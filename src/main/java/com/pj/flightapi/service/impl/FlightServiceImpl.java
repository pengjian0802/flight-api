package com.pj.flightapi.service.impl;

import com.pj.flightapi.dto.FlightDetailDto;
import com.pj.flightapi.dto.FlightDto;
import com.pj.flightapi.dto.FlightRequest;
import com.pj.flightapi.dto.FlightSeatDto;
import com.pj.flightapi.entity.Flight;
import com.pj.flightapi.entity.FlightSeat;
import com.pj.flightapi.repository.FlightRepository;
import com.pj.flightapi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
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
            flightDto.setAirline(flight.getAirline());
            flightDto.setFlightNumber(flight.getFlightNumber());
            flightDto.setAircraft(flight.getAircraft());
            flightDto.setDepartureCity(flight.getDepartureCity());
            flightDto.setArrivalCity(flight.getArrivalCity());
            flightDto.setDepartureAirport(flight.getDepartureAirport());
            flightDto.setArrivalAirport(flight.getArrivalAirport());
            flightDto.setDepartureTime(flight.getDepartureTime());
            flightDto.setArrivalTime(flight.getArrivalTime());
            flightDto.setTotalSeats(flight.getTotalSeats());
            flightDto.setAvailableSeats(flight.getAvailableSeats());
            flightDto.setDuration(flight.getDuration());
            List<FlightSeat> flightSeats = flight.getSeats();
            Map<FlightSeat.SeatType, Double> priceMap = new HashMap<>();
            for (FlightSeat flightSeat : flightSeats) {
                priceMap.put(flightSeat.getSeatType(), flightSeat.getPrice());
            }
            flightDto.setPriceMap(priceMap);
            flightDtos.add(flightDto);
        }
        return flightDtos;
    }

    @Override
    public FlightDetailDto queryFlightDetail(Long id) {
        Flight flight = flightRepository.findById(id).orElse(null);
        FlightDetailDto flightDetailDto = new FlightDetailDto();
        flightDetailDto.setId(flight.getId());
        flightDetailDto.setAirline(flight.getAirline());
        flightDetailDto.setFlightNumber(flight.getFlightNumber());
        flightDetailDto.setAircraft(flight.getAircraft());
        flightDetailDto.setDepartureCity(flight.getDepartureCity());
        flightDetailDto.setArrivalCity(flight.getArrivalCity());
        flightDetailDto.setDepartureAirport(flight.getDepartureAirport());
        flightDetailDto.setArrivalAirport(flight.getArrivalAirport());
        flightDetailDto.setDepartureTime(flight.getDepartureTime());
        flightDetailDto.setArrivalTime(flight.getArrivalTime());
        flightDetailDto.setTotalSeats(flight.getTotalSeats());
        flightDetailDto.setAvailableSeats(flight.getAvailableSeats());
        flightDetailDto.setDuration(flight.getDuration());
        List<FlightSeat> flightSeats = flight.getSeats();
        List<FlightSeatDto> flightSeatDtos = new ArrayList<>();
        for (FlightSeat flightSeat : flightSeats) {
            FlightSeatDto flightSeatDto = new FlightSeatDto();
            flightSeatDto.setId(flightSeat.getId());
            flightSeatDto.setSeatNumber(flightSeat.getSeatNumber());
            flightSeatDto.setSeatType(flightSeat.getSeatType());
            flightSeatDto.setPrice(flightSeat.getPrice());
            flightSeatDto.setIsAvailable(flightSeat.getIsAvailable());
            flightSeatDtos.add(flightSeatDto);
        }
        flightDetailDto.setSeats(flightSeatDtos);
        return flightDetailDto;
    }
}
