package com.bookstore.ordering.system.order.service.domain.mapper;

import com.bookstore.ordering.system.domain.valueobject.BookStoreId;
import com.bookstore.ordering.system.domain.valueobject.CustomerId;
import com.bookstore.ordering.system.domain.valueobject.Money;
import com.bookstore.ordering.system.domain.valueobject.ProductId;
import com.bookstore.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.bookstore.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.bookstore.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.bookstore.system.order.service.entity.BookStore;
import com.bookstore.system.order.service.entity.Order;
import com.bookstore.system.order.service.entity.OrderItem;
import com.bookstore.system.order.service.entity.Product;
import com.bookstore.system.order.service.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {
    public BookStore createOrderCommandToBookStore(CreateOrderCommand createOrderCommand) {
        return BookStore.builder()
                .bookStoreId(new BookStoreId(createOrderCommand.getBookStoreId()))
                .products(createOrderCommand.getItems().stream().map(orderItem ->
                                new Product(new ProductId(orderItem.getProductId())))
                        .collect(Collectors.toList()))
                .build();

    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .bookStoreId(new BookStoreId(createOrderCommand.getBookStoreId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order, String message) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .message(message)
                .build();
    }

    private List<OrderItem> orderItemsToOrderItemEntities(List<com.bookstore.ordering.system.order.service.domain.dto.create.OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem -> OrderItem.builder()
                        .product(new Product(new ProductId(orderItem.getProductId())))
                        .price(new Money(orderItem.getPrice()))
                        .quantity(orderItem.getQuantity())
                        .subTotal(new Money(orderItem.getSubTotal()))
                        .build()).collect(Collectors.toList());
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress address) {
        return new StreetAddress(
                UUID.randomUUID(),
                address.getStreet(),
                address.getPostalCode(),
                address.getCity()
        );
    }
}
