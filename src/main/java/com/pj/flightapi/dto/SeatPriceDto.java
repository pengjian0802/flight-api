package com.pj.flightapi.dto;

import com.pj.flightapi.entity.FlightSeat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatPriceDto {
    private FlightSeat.SeatType seatType;
    private Double price;
}
