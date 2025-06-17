package com.pj.flightapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightRequest {

    private String departureCity;
    private String arrivalCity;
    private LocalDate departureDate;
    private Integer passengers;
}
