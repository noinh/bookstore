package com.bookstore.system.order.service;

import com.bookstore.system.order.service.entity.BookStore;
import com.bookstore.system.order.service.entity.Order;
import com.bookstore.system.order.service.event.OrderCancelledEvent;
import com.bookstore.system.order.service.event.OrderCreatedEvent;
import com.bookstore.system.order.service.event.OrderPaidEvent;

import java.util.List;

public interface OrderDomainService {
    OrderCreatedEvent validateAndInitiateOrder(Order order, BookStore bookStore);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}
