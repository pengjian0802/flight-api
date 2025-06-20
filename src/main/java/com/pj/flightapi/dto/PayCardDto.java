package com.pj.flightapi.dto;

import com.pj.flightapi.entity.PayCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayCardDto {
    private Long id;
    private Long userId;
    private PayCard.CardType cardType;
    private String cardNumber;
    private String cvv;
    private LocalDate expireDate;
    private String cardHolderName;
}
