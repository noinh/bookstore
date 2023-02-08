package com.bookstore.ordering.system.order.service.domain.ports.input.message.listener.bookstoreapproval;

import com.bookstore.ordering.system.order.service.domain.dto.message.BookStoreApprovalResponse;

public interface BookStoreApprovalResponseMessageListener {
    void orderApproved(BookStoreApprovalResponse bookStoreApprovalResponse);

    void orderRejected(BookStoreApprovalResponse bookStoreApprovalResponse);
}
