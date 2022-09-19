package com.code.paymentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "TRANSACTION_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long orderId;
    @Column(name = "mode")
    private String paymentMode;
    private String referenceNumber;
    private Instant paymentDate;
    @Column(name = "status")
    private String paymentStatus;
    private long amount;

}
