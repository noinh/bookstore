package com.bookstore.ordering.system.order.service.domain;

import com.bookstore.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.bookstore.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.bookstore.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.bookstore.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.bookstore.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderTrackingCommandHandler orderTrackingCommandHandler;

    public OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler, OrderTrackingCommandHandler orderTrackingCommandHandler) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.orderTrackingCommandHandler = orderTrackingCommandHandler;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return null;
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return null;
    }
}
