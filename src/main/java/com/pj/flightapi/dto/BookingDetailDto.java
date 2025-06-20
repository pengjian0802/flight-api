package com.pj.flightapi.dto;

import com.pj.flightapi.entity.FlightSeat;
import com.pj.flightapi.entity.PayCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetailDto {

    private Long id;
    private UserDto user;
    // Flight
    private FlightDto flight;
    // Seat
    private FlightSeatDto seat;
    // PayCard
    private PayCardDto payCard;
    // Booking
    private String status;
    private LocalDateTime bookingTime;
    private Double totalPrice;
}
