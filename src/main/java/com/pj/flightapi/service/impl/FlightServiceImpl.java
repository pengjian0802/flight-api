package com.pj.flightapi.service.impl;

import com.pj.flightapi.dto.FlightDto;
import com.pj.flightapi.dto.FlightRequest;
import com.pj.flightapi.entity.Flight;
import com.pj.flightapi.repository.FlightRepository;
import com.pj.flightapi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;


    @Override
    public List<FlightDto> queryFlightList(FlightRequest request) {
        List<FlightDto> flightDtos = new ArrayList<>();
        List<Flight> flights = flightRepository.findAllByCondition(request.getDepartureCity(), request.getArrivalCity(), request.getDepartureDate(), request.getPassengers());
        for (Flight flight : flights) {
            FlightDto flightDto = new FlightDto();
            flightDto.setId(flight.getId());
            flightDto.setDepartureCity(flight.getDepartureCity());
            flightDto.setArrivalCity(flight.getArrivalCity());
            flightDto.setDepartureAirport(flight.getDepartureAirport());
            flightDto.setArrivalAirport(flight.getArrivalAirport());
            flightDto.setDepartureTime(flight.getDepartureTime());
            flightDto.setArrivalTime(flight.getArrivalTime());
            flightDto.setTotalSeats(flight.getTotalSeats());
            flightDto.setAvailableSeats(flight.getAvailableSeats());
            flightDto.setDuration(flight.getDuration());
            flightDto.setSeats(flight.getSeats());
            flightDtos.add(flightDto);
        }
        return flightDtos;
    }
}
