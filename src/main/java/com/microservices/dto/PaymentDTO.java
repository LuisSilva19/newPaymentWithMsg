package com.microservices.dto;

import com.microservices.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO {
    private Integer id;
    private BigDecimal value;
    private String name;
    private String number;
    private String expiration;
    private String cod;
    private Status status;
    private Integer orderId;
    private Integer formOfPayment;
}
