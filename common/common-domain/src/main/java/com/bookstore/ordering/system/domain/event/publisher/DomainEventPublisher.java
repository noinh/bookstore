package com.bookstore.ordering.system.domain.event.publisher;

import com.bookstore.ordering.system.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {
    void publish(T domainEvent);
}
