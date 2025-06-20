package com.pj.flightapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pj.flightapi.entity.FlightSeat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private Long id;
    private Long userId;
    private Long flightId;
    private Long payCardId;
    private String flightNumber;
    private String airline;
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Long seatId;
    private String seatNumber;
    private FlightSeat.SeatType seatType;
    private Double price;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bookingTime;
    private Double totalPrice;
}
