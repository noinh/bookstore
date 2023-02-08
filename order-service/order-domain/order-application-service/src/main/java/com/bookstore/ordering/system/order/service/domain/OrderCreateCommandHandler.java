package com.bookstore.ordering.system.order.service.domain;

import com.bookstore.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.bookstore.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.bookstore.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.bookstore.ordering.system.order.service.domain.ports.output.repository.BookStoreRepository;
import com.bookstore.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import com.bookstore.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.bookstore.system.order.service.OrderDomainService;
import com.bookstore.system.order.service.entity.BookStore;
import com.bookstore.system.order.service.entity.Customer;
import com.bookstore.system.order.service.entity.Order;
import com.bookstore.system.order.service.event.OrderCreatedEvent;
import com.bookstore.system.order.service.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderCreateCommandHandler {
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BookStoreRepository bookStoreRepository;
    private final OrderDataMapper orderDataMapper;

    public OrderCreateCommandHandler(OrderDomainService orderDomainService,
                                     OrderRepository orderRepository,
                                     CustomerRepository customerRepository,
                                     BookStoreRepository bookStoreRepository,
                                     OrderDataMapper orderDataMapper) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.bookStoreRepository = bookStoreRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        BookStore bookStore = checkBookStore(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, bookStore);
        Order orderResult = saveOrder(order);
        log.info("Order is created with id: {}", orderResult.getId().getValue());
        return orderDataMapper.orderToCreateOrderResponse(orderResult, "Order is created");
    }

    private BookStore checkBookStore(CreateOrderCommand createOrderCommand) {
        BookStore bookStore = orderDataMapper.createOrderCommandToBookStore(createOrderCommand);
        Optional<BookStore> optionalBookStore = bookStoreRepository.findBookStoreInformation(bookStore)
        if (optionalBookStore.isEmpty()) {
            log.warn("Could not find BookStore with id: {}", createOrderCommand.getBookStoreId());
            throw new OrderDomainException("Could not find BookStore with id: " + createOrderCommand.getBookStoreId());
        }
        return optionalBookStore.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);

        if (customer.isEmpty()) {
            log.warn("Could not find customer with id: {}", customerId);
            throw new OrderDomainException("Could not find customer with id: " + customerId);
        }
    }

    private Order saveOrder(Order order) {
        Order orderResult = orderRepository.save(order)
        if (orderResult == null) {
            log.error("Could not save order!");
            throw new OrderDomainException("Could not save order!");
        }
        log.info("Order is saved with id: {}", order.getId().getValue());
        return orderResult;
    }
}
