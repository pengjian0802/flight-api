package com.pj.flightapi.service;

import com.pj.flightapi.dto.BookingDetailDto;
import com.pj.flightapi.dto.BookingDto;
import com.pj.flightapi.dto.BookingRequest;

import java.util.List;


public interface BookingService {

    List<BookingDto> queryBookingList(Long userId);

    BookingDetailDto queryBookingDetail(BookingRequest request);

    BookingDto saveBooking(BookingDto bookingDto);

    BookingDto updateStatus(Long id, String status);
}
