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
public class BookingRequest {

    private Long userId;
    private Long flightId;
    private Long seatId;
    private Long bookingId;
    private Long payCardId;
}
