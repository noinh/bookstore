package com.bookstore.system.order.service.entity;

import com.bookstore.ordering.system.domain.entity.AggregateRoot;
import com.bookstore.ordering.system.domain.valueobject.BookStoreId;

import java.util.List;

public class BookStore extends AggregateRoot<BookStoreId> {
    private final List<Product> products;
    private boolean active;

    private BookStore(Builder builder) {
        super.setId(builder.bookStoreId);
        products = builder.products;
        setActive(builder.active);
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static final class Builder {
        private BookStoreId bookStoreId;
        private List<Product> products;
        private boolean active;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder bookStoreId(BookStoreId val) {
            bookStoreId = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public BookStore build() {
            return new BookStore(this);
        }
    }
}
