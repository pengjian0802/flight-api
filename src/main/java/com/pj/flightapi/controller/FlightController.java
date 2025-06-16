package com.pj.flightapi.controller;

import com.pj.flightapi.common.Result;
import com.pj.flightapi.dto.FlightResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/flight")
public class FlightController {

    @PostMapping("/list")
    public Result<List<FlightResponse>> queryFlightList() {
        return Result.success(new ArrayList<>());
    }
}
