package com.assignment.bidding.dto;

import com.assignment.bidding.model.Session;
import com.assignment.bidding.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ItemDto {
    private Long id;

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "price is required")
    private BigDecimal price;

    private String status;

    private SessionDto session;

}
