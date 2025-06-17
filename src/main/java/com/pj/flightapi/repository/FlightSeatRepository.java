package com.pj.flightapi.repository;

import com.pj.flightapi.entity.FlightSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightSeatRepository extends JpaRepository<FlightSeat, Long> {
}
