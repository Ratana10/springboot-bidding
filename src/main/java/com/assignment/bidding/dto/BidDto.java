package com.assignment.bidding.dto;

import com.assignment.bidding.enums.BidStatus;
import com.assignment.bidding.model.Item;
import com.assignment.bidding.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BidDto {
    private Long id;

    @NotNull(message = "amount is required")
    private BigDecimal amount;

    @NotNull(message = "itemId is required")
    private Long itemId;

    @NotNull(message = "bidderId is required")
    private Long bidderId;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private BidStatus status;
}
