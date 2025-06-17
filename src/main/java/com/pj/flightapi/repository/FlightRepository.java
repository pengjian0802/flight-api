package com.pj.flightapi.repository;

import com.pj.flightapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE f.departureCity = :departureCity AND f.arrivalCity = :arrivalCity AND FUNCTION('DATE', f.departureTime) = :departureDate AND f.availableSeats >= :passengers")
    List<Flight> findAllByCondition(@Param("departureCity") String departureCity, @Param("arrivalCity") String arrivalCity, @Param("departureDate") LocalDate departureDate, @Param("passengers") Integer passengers);
}
