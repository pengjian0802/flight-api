package com.pj.flightapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flight_seats")
public class FlightSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 5)
    private String seatNumber; // 座位编号（如 "1A", "2B"）

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SeatType seatType; // 座位类型

    @Column(nullable = false)
    private Double price; // 价格

    @Column(nullable = false)
    private Boolean isAvailable; // 是否可用

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight; // 所属航班

    // 座位类型枚举
    public enum SeatType {
        ECONOMY, // 经济舱
        BUSINESS, // 商务舱
        FIRST_CLASS // 头等舱
    }
}