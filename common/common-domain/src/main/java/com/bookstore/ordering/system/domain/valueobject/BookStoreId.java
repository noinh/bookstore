package com.bookstore.ordering.system.domain.valueobject;

import java.util.UUID;

public class BookStoreId extends BaseId<UUID> {
    public BookStoreId(UUID value) {
        super(value);
    }
}
