package com.gg.sms.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SchoolDto {
    private Long id;
    private String name;
    private String address;
    private String email;
    private BigDecimal accountBalance = BigDecimal.valueOf(0);
}
