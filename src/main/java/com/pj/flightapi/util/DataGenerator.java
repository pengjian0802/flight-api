package com.pj.flightapi.util;

import com.pj.flightapi.entity.Flight;
import com.pj.flightapi.entity.FlightSeat;
import com.pj.flightapi.entity.FlightSeat.SeatType;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataGenerator {

    private static final Random random = new Random();
    private static final String[] AIRLINES = {"China Airlines", "China Southern Airlines", "China Eastern Airlines"};
    private static final String[] DEPARTURE_CITIES = {"Beijing", "Shanghai", "Guangzhou", "Shenzhen", "Chengdu"};
    private static final String[] ARRIVAL_CITIES = {"Beijing", "Shanghai", "Guangzhou", "Shenzhen", "Chengdu"};
    private static final String[] AIRCRAFTS = {"Airbus A320", "Boeing 737-800", "Boeing 787-9", "Airbus A330-300", "Boeing 747-400"};
    private static final int SEAT_ROWS = 30;
    private static final int SEATS_PER_ROW = 6;

    public static void main(String[] args) {
        List<Flight> flights = generateFlights(100);
        StringBuilder flightBuilder = new StringBuilder("INSERT INTO flights (airline, flight_number, aircraft, departure_city, departure_airport, arrival_city, arrival_airport, departure_time, arrival_time, total_seats, available_seats, duration) VALUES");
        StringBuilder seatBuilder = new StringBuilder("INSERT INTO flight_seats (seat_number, seat_type, price, is_available, flight_id) VALUES");
        // 这里可以将 flights 列表保存到数据库中，示例中只是简单打印
        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            flightBuilder.append("('");
            flightBuilder.append(flight.getAirline()).append("', '");
            flightBuilder.append(flight.getFlightNumber()).append("', '");
            flightBuilder.append(flight.getAircraft()).append("', '");
            flightBuilder.append(flight.getDepartureCity()).append("', '");
            flightBuilder.append(flight.getDepartureAirport()).append("', '");
            flightBuilder.append(flight.getArrivalCity()).append("', '");
            flightBuilder.append(flight.getArrivalAirport()).append("', '");
            flightBuilder.append(flight.getDepartureTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("', '");
            flightBuilder.append(flight.getArrivalTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("', '");
            flightBuilder.append(flight.getTotalSeats()).append("', '");
            flightBuilder.append(flight.getAvailableSeats()).append("', '");
            flightBuilder.append(flight.getDuration()).append("')");
            if (i == flights.size() - 1) {
                flightBuilder.append(";\n");
            } else {
                flightBuilder.append(",\n");
            }
            List<FlightSeat> flightSeats = generateSeats(30);
            for (int j = 0; j < flightSeats.size(); j++) {
                FlightSeat seat = flightSeats.get(j);
                seatBuilder.append("('");
                seatBuilder.append(seat.getSeatNumber()).append("', '");
                seatBuilder.append(seat.getSeatType()).append("', '");
                seatBuilder.append(seat.getPrice()).append("', '");
                seatBuilder.append(seat.getIsAvailable()).append("', '");
                seatBuilder.append(i+4).append("')");
                if (j == flightSeats.size() - 1) {
                    seatBuilder.append(";\n");
                } else {
                    seatBuilder.append(",\n");
                }
            }
        }

        System.out.println("************************ Flight Start ***************************************");
        System.out.println(flightBuilder.toString());
        System.out.println("************************ Flight End ***************************************");
        System.out.println("************************ Flight Seat Start ***************************************");
        System.out.println(seatBuilder.toString());
        System.out.println("************************ Flight Seat End ***************************************");
    }

    public static List<Flight> generateFlights(int count) {
        List<Flight> flights = new ArrayList<>();
        LocalDateTime start = YearMonth.now().atDay(1).atStartOfDay();
        LocalDateTime end = YearMonth.now().plusMonths(1).atEndOfMonth().atTime(23, 59, 59);

        for (int i = 0; i < count; i++) {
            Flight flight = generateFlight(start, end);
            if (flight.getArrivalCity().equals(flight.getDepartureCity())) {
                continue;
            }
            flights.add(flight);
        }
        return flights;
    }

    private static Flight generateFlight(LocalDateTime start, LocalDateTime end) {
        String airline = AIRLINES[random.nextInt(AIRLINES.length)];
        String flightNumber = "F" + random.nextInt(1000);
        String aircraft = AIRCRAFTS[random.nextInt(AIRCRAFTS.length)];
        String departureCity = DEPARTURE_CITIES[random.nextInt(DEPARTURE_CITIES.length)];
        String arrivalCity = ARRIVAL_CITIES[random.nextInt(ARRIVAL_CITIES.length)];
        LocalDateTime departureTime = randomDateTime(start, end);
        long durationHours = random.nextInt(1, 10);
        LocalDateTime arrivalTime = departureTime.plusHours(durationHours);
        int totalSeats = SEAT_ROWS * SEATS_PER_ROW;
        int availableSeats = 30;
        String duration = durationHours + "h";

        Flight flight = Flight.builder()
                .airline(airline)
                .flightNumber(flightNumber)
                .aircraft(aircraft)
                .departureCity(departureCity)
                .departureAirport("Airport of " + departureCity)
                .arrivalCity(arrivalCity)
                .arrivalAirport("Airport of " + arrivalCity)
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .totalSeats(totalSeats)
                .availableSeats(availableSeats)
                .duration(duration)
                .build();

        return flight;
    }

    private static List<FlightSeat> generateSeats(int availableSeats) {
        List<FlightSeat> seats = new ArrayList<>();
        Map<SeatType, Double> priceMap = getPrice();
        for (int row = 1; row <= SEAT_ROWS; row++) {
            for (int col = 0; col < SEATS_PER_ROW; col++) {
                String seatNumber = row + "" + (char) ('A' + col);
                SeatType seatType = getSeatType(row);
                boolean isAvailable = availableSeats > 0;
                if (isAvailable) {
                    availableSeats--;
                }

                FlightSeat seat = FlightSeat.builder()
                        .seatNumber(seatNumber)
                        .seatType(seatType)
                        .price(priceMap.get(seatType))
                        .isAvailable(isAvailable)
                        .build();
                seats.add(seat);
            }
        }
        return seats;
    }

    private static SeatType getSeatType(int row) {
        if (row <= 5) {
            return SeatType.FIRST_CLASS;
        } else if (row <= 15) {
            return SeatType.BUSINESS;
        } else {
            return SeatType.ECONOMY;
        }
    }

    private static Map<SeatType, Double> getPrice() {
        Map<SeatType, Double> priceMap = new HashMap<>();
        double FIRST_CLASS_price = random.nextInt(1000, 5000);
        priceMap.put(SeatType.FIRST_CLASS, FIRST_CLASS_price);
        double BUSINESS_price = random.nextInt(500, 1000);
        priceMap.put(SeatType.BUSINESS, BUSINESS_price);
        double ECONOMY_price = random.nextInt(100, 500);
        priceMap.put(SeatType.ECONOMY, ECONOMY_price);
        return priceMap;
    }

    private static LocalDateTime randomDateTime(LocalDateTime start, LocalDateTime end) {
        long startEpoch = start.toEpochSecond(ZoneOffset.of("+08:00"));
        long endEpoch = end.toEpochSecond(ZoneOffset.of("+08:00"));
        long randomEpoch = startEpoch + random.nextInt((int) (endEpoch - startEpoch));
        return LocalDateTime.ofEpochSecond(randomEpoch, 0, ZoneOffset.of("+08:00"));
    }
}