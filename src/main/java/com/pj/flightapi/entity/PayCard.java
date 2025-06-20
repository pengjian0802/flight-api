package com.pj.flightapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "pay_card")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private CardType cardType;
    private String cardNumber;
    private String cvv;
    private LocalDate expireDate;
    private String cardHolderName;

    public enum CardType {
        Visa, MasterCard, AmericanExpress
    }
}
