package com.assignment.bidding.model;

import com.assignment.bidding.enums.Status;
import jakarta.persistence.*;
import jdk.jfr.Registered;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@NoArgsConstructor
@Registered
@Builder
@AllArgsConstructor
@Entity
@Data
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private Status status;

    @OneToOne
    private Session session;

}
