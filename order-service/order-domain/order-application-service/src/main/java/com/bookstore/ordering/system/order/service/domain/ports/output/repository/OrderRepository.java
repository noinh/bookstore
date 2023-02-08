package com.bookstore.ordering.system.order.service.domain.ports.output.repository;

import com.bookstore.system.order.service.entity.Order;
import com.bookstore.system.order.service.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
