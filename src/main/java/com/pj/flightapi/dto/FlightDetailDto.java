package com.pj.flightapi.dto;

import com.pj.flightapi.entity.FlightSeat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightDetailDto {

    private Long id;
    private String airline;
    private String flightNumber;
    private String aircraft;
    private String departureCity;
    private String departureAirport;
    private String arrivalCity;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String duration;
    private Integer totalSeats;
    private Integer availableSeats;
    private List<FlightSeatDto> seats;
}
