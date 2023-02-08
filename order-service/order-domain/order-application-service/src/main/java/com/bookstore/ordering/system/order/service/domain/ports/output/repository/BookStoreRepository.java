package com.bookstore.ordering.system.order.service.domain.ports.output.repository;

import com.bookstore.system.order.service.entity.BookStore;

import java.util.Optional;

public interface BookStoreRepository {
    Optional<BookStore> findBookStoreInformation(BookStore bookStore);
}
