package com.bookstore.ordering.system.order.service.domain.ports.output.repository;

import com.bookstore.system.order.service.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);
}
