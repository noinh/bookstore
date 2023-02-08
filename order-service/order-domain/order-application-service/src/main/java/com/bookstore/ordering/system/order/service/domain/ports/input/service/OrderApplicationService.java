package com.bookstore.ordering.system.order.service.domain.ports.input.service;

import com.bookstore.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.bookstore.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.bookstore.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.bookstore.ordering.system.order.service.domain.dto.track.TrackOrderResponse;

import javax.validation.Valid;

public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
