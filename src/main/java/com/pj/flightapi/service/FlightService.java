package com.pj.flightapi.service;

import com.pj.flightapi.dto.FlightDto;
import com.pj.flightapi.dto.FlightRequest;
import com.pj.flightapi.entity.Flight;

import java.util.List;


public interface FlightService {

    List<FlightDto> queryFlightList(FlightRequest request);

    FlightDto queryFlightDetail(Long id);
}
