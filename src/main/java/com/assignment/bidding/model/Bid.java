package com.assignment.bidding.model;

import com.assignment.bidding.config.auditing.AuditingEntity;
import com.assignment.bidding.enums.BidStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "bids")
public class Bid extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "bidder_id")
    private User bidder;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private BidStatus status;
}
