package com.bookstore.ordering.system.order.service.domain.dto.message;

import com.bookstore.ordering.system.domain.valueobject.OrderApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class BookStoreApprovalResponse {
    private String id;
    private String sagaId;
    private String orderId;
    private String bookStoreId;
    private Instant createdAt;
    private OrderApprovalStatus orderApprovalStatus;
    private List<String> failureMessage;
}
