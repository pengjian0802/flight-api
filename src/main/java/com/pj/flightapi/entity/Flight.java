package com.pj.flightapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String flightNumber; // 航班号

    @Column(nullable = false)
    private String departureCity; // 出发城市

    @Column(nullable = false)
    private String departureAirport; // 出发机场

    @Column(nullable = false)
    private String arrivalCity; // 目的城市

    @Column(nullable = false)
    private String arrivalAirport; // 到达机场

    @Column(nullable = false)
    private LocalDateTime departureTime; // 起飞时间

    @Column(nullable = false)
    private LocalDateTime arrivalTime; // 降落时间

    @Column(nullable = false)
    private Integer totalSeats; // 总座位数

    @Column(nullable = false)
    private Integer availableSeats; // 可用座位数

    @Column(nullable = false)
    private String duration; // 耗时

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightSeat> seats; // 航班座位列表
}