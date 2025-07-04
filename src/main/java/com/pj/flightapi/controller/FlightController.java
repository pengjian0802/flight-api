package com.pj.flightapi.controller;

import com.pj.flightapi.common.Result;
import com.pj.flightapi.dto.FlightDetailDto;
import com.pj.flightapi.dto.FlightDto;
import com.pj.flightapi.dto.FlightRequest;
import com.pj.flightapi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/list")
    public Result<List<FlightDto>> queryFlightList(@RequestBody FlightRequest flightRequest) {
        List<FlightDto> flightDtos = flightService.queryFlightList(flightRequest);
        return Result.success(flightDtos);
    }

    @GetMapping("/detail/{flightId}")
    public Result<FlightDetailDto> getFlightDetail(@PathVariable Long flightId) {
        FlightDetailDto flightDetailDto = flightService.queryFlightDetail(flightId);
        return Result.success(flightDetailDto);
    }
}
