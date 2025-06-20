package com.pj.flightapi.service.impl;

import com.pj.flightapi.dto.*;
import com.pj.flightapi.entity.*;
import com.pj.flightapi.repository.*;
import com.pj.flightapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightSeatRepository flightSeatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PayCardRepository payCardRepository;

    @Override
    public List<BookingDto> queryBookingList(Long userId) {
        List<Booking> bookingList = bookingRepository.findAllByCondition(userId);
        List<BookingDto> bookingDtoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(bookingList)) {
            for (Booking booking : bookingList) {
                BookingDto bookingDto = new BookingDto();
                bookingDto.setId(booking.getId());
                bookingDto.setUserId(booking.getUserId());
                bookingDto.setFlightId(booking.getFlightId());
                Flight flight = flightRepository.findById(booking.getFlightId()).orElse(null);
                if (flight != null) {
                    bookingDto.setFlightNumber(flight.getFlightNumber());
                    bookingDto.setAirline(flight.getAirline());
                    bookingDto.setDepartureCity(flight.getDepartureCity());
                    bookingDto.setArrivalCity(flight.getArrivalCity());
                    bookingDto.setDepartureTime(flight.getDepartureTime());
                    bookingDto.setArrivalTime(flight.getArrivalTime());
                }
                bookingDto.setStatus(booking.getStatus());
                bookingDto.setBookingTime(booking.getBookingTime());
                bookingDto.setTotalPrice(booking.getTotalPrice());
                bookingDtoList.add(bookingDto);
            }
        }
        return bookingDtoList;
    }

    @Override
    public BookingDetailDto queryBookingDetail(BookingRequest request) {
        BookingDetailDto bookingDetailDto = new BookingDetailDto();
        // userId
        if (null != request.getUserId()) {
            User user = userRepository.findById(request.getUserId()).orElse(null);
            if (user != null) {
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setFirstName(user.getFirstName());
                userDto.setLastName(user.getLastName());
                userDto.setCountry(user.getCountry());
                userDto.setPhone(user.getPhone());
                userDto.setEmail(user.getEmail());
                bookingDetailDto.setUser(userDto);
            }

        }

        // flight
        if (null != request.getFlightId()) {
            Flight flight = flightRepository.findById(request.getFlightId()).orElse(null);
            if (flight != null) {
                FlightDto flightDto = new FlightDto();
                flightDto.setId(flight.getId());
                flightDto.setAirline(flight.getAirline());
                flightDto.setFlightNumber(flight.getFlightNumber());
                flightDto.setAircraft(flight.getAircraft());
                flightDto.setDepartureCity(flight.getDepartureCity());
                flightDto.setDepartureAirport(flight.getDepartureAirport());
                flightDto.setArrivalCity(flight.getArrivalCity());
                flightDto.setArrivalAirport(flight.getArrivalAirport());
                flightDto.setDepartureTime(flight.getDepartureTime());
                flightDto.setArrivalTime(flight.getArrivalTime());
                flightDto.setDuration(flight.getDuration());
                bookingDetailDto.setFlight(flightDto);
            }
        }

        // seat
        if (null != request.getSeatId()) {
            FlightSeat flightSeat = flightSeatRepository.findById(request.getSeatId()).orElse(null);
            if (flightSeat!= null) {
                FlightSeatDto flightSeatDto = new FlightSeatDto();
                flightSeatDto.setId(flightSeat.getId());
                flightSeatDto.setSeatNumber(flightSeat.getSeatNumber());
                flightSeatDto.setSeatType(flightSeat.getSeatType());
                flightSeatDto.setPrice(flightSeat.getPrice());
                bookingDetailDto.setSeat(flightSeatDto);
            }
        }

        // payCard
        if (null != request.getPayCardId()) {
            PayCard payCard = payCardRepository.findById(request.getPayCardId()).orElse(null);
            if (payCard!= null) {
                PayCardDto payCardDto = new PayCardDto();
                payCardDto.setId(payCard.getId());
                payCardDto.setCardType(payCard.getCardType());
                payCardDto.setCardNumber(payCard.getCardNumber());
                payCardDto.setCvv(payCard.getCvv());
                payCardDto.setExpireDate(payCard.getExpireDate());
                payCardDto.setCardHolderName(payCard.getCardHolderName());
                bookingDetailDto.setPayCard(payCardDto);
            }
        }

        // booking
        if (null != request.getBookingId()) {
            Booking booking = bookingRepository.findById(request.getBookingId()).orElse(null);
            if (booking!= null) {
                bookingDetailDto.setStatus(booking.getStatus());
                bookingDetailDto.setBookingTime(booking.getBookingTime());
                bookingDetailDto.setTotalPrice(booking.getTotalPrice());
            }
        }
        return bookingDetailDto;
    }

    @Override
    public BookingDto saveBooking(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setUserId(bookingDto.getUserId());
        booking.setFlightId(bookingDto.getFlightId());
        booking.setPayCardId(bookingDto.getPayCardId());
        booking.setStatus(bookingDto.getStatus());
        booking.setBookingTime(bookingDto.getBookingTime());
        booking.setTotalPrice(bookingDto.getTotalPrice());
        Booking entity = bookingRepository.save(booking);
        bookingDto.setId(entity.getId());
        return bookingDto;
    }

    @Override
    public BookingDto updateStatus(Long id, String status) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) {
            return null;
        }
        booking.setStatus(status);
        bookingRepository.save(booking);
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setUserId(booking.getUserId());
        bookingDto.setFlightId(booking.getFlightId());
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setBookingTime(booking.getBookingTime());
        bookingDto.setTotalPrice(booking.getTotalPrice());
        return bookingDto;
    }
}
