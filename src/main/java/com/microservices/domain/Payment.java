package com.microservices.domain;

import com.microservices.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Positive
    private BigDecimal value;

    @Size(max=100)
    private String name;

    @Size(max=19)
    private String number;

    @Size(max=7)
    private String expiration;

    @Size(min=3, max=3)
    private String cod;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Integer orderId;

    @NotNull
    private Integer formOfPayment;
}
