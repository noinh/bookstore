package com.bookstore.system.order.service;

import com.bookstore.system.order.service.entity.BookStore;
import com.bookstore.system.order.service.entity.Order;
import com.bookstore.system.order.service.entity.Product;
import com.bookstore.system.order.service.event.OrderCancelledEvent;
import com.bookstore.system.order.service.event.OrderCreatedEvent;
import com.bookstore.system.order.service.event.OrderPaidEvent;
import com.bookstore.system.order.service.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

    public static final String UTC = "UTC";

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, BookStore bookStore) {
        validateBookStore(bookStore);
        setOrderProductInformation(order, bookStore);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id: {} is initialed ", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id: {} is paid", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void approveOrder(Order order) {
        order.approved();
        log.info("Order with id: {} is approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order  payment is cancelling for order id: {} ", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id: {} is cancelled", order.getId().getValue());
    }

    private void validateBookStore(BookStore bookStore) {
        if (!bookStore.isActive()) {
            throw new OrderDomainException("BookStore with id : {}" + bookStore.getId().getValue() + " is currently not active!");
        }
    }

    private void setOrderProductInformation(Order order, BookStore bookStore) {
        order.getItems().forEach(orderItem -> bookStore.getProducts().forEach(bookStoreProduct -> {
            Product currentProduct = orderItem.getProduct();
            if (currentProduct.equals(bookStoreProduct)) {
                currentProduct.updateWithConfirmedNameAndPrice(bookStoreProduct.getName(), bookStoreProduct.getPrice());
            }
        }));
    }
}
