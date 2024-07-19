package com.assignment.bidding.dto;

import com.assignment.bidding.model.Bid;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SessionDto {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private BigDecimal highestAmount;

    private Long winningBidId;
}
