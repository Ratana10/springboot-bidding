package com.assignment.bidding.model;

import com.assignment.bidding.config.auditing.AuditingEntity;
import com.assignment.bidding.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "sessions")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Session extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private BigDecimal highestAmount;

    @ManyToOne
    @JoinColumn(name = "winning_bid_id")
    private Bid winningBid;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    private Item item;
}
