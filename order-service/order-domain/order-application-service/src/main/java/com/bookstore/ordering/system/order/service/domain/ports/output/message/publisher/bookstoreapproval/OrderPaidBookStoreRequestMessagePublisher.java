package com.bookstore.ordering.system.order.service.domain.ports.output.message.publisher.bookstoreapproval;

import com.bookstore.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.bookstore.system.order.service.event.OrderPaidEvent;

public interface OrderPaidBookStoreRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
