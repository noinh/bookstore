package com.bookstore.ordering.system.order.service.domain.ports.output.message.publisher.payment;

import com.bookstore.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.bookstore.system.order.service.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
