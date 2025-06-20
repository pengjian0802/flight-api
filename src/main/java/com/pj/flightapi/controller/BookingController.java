package com.pj.flightapi.controller;

import com.pj.flightapi.common.Result;
import com.pj.flightapi.dto.BookingDetailDto;
import com.pj.flightapi.dto.BookingDto;
import com.pj.flightapi.dto.BookingRequest;
import com.pj.flightapi.entity.Booking;
import com.pj.flightapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/list/{userId}")
    public Result<List<BookingDto>> queryBookingList(@PathVariable Long userId) {
        List<BookingDto> bookingDtos = bookingService.queryBookingList(userId);
        return Result.success(bookingDtos);
    }

    @PostMapping("/detail")
    public Result<BookingDetailDto> queryBookingDetail(@RequestBody BookingRequest request) {
        BookingDetailDto bookingDetailDto = bookingService.queryBookingDetail(request);
        return Result.success(bookingDetailDto);
    }

    @PostMapping("/save")
    public Result<BookingDto> saveBooking(@RequestBody BookingDto bookingDto) {
        BookingDto dto = bookingService.saveBooking(bookingDto);
        return Result.success(dto);
    }

    @GetMapping("/updateStatus/{id}/{status}")
    public Result<BookingDto> updateStatus(@PathVariable Long id, @PathVariable String status) {
        BookingDto bookingDto = bookingService.updateStatus(id, status);
        return Result.success(bookingDto);
    }
}
