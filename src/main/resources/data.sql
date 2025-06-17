-- 插入示例用户数据
INSERT INTO "user" (first_name, last_name, country, phone, email, password)
VALUES ('John', 'Doe', 'USA', '123-456-7890', 'john.doe@example.com', 'password123');

-- 插入更多示例航班数据
INSERT INTO flights (flight_number, departure_city, departure_airport, arrival_city, arrival_airport, departure_time, arrival_time, total_seats, available_seats, duration)
VALUES
('FA123', 'New York', 'JFK', 'Los Angeles', 'LAX', '2024-01-01 10:00:00', '2024-01-01 13:00:00', 200, 150, '3 hours'),
('FA234', 'London', 'Heathrow', 'Paris', 'Charles de Gaulle', '2024-01-02 14:30:00', '2024-01-02 16:30:00', 150, 120, '2 hours'),
('FA345', 'Tokyo', 'Narita', 'Singapore', 'Changi', '2024-01-03 09:00:00', '2024-01-03 16:00:00', 250, 200, '7 hours');

-- 插入更多示例航班座位数据
-- 航班 FA123 的座位
INSERT INTO flight_seats (seat_number, seat_type, price, is_available, flight_id)
VALUES
('1A', 'FIRST_CLASS', 500.0, TRUE, 1),
('2A', 'FIRST_CLASS', 500.0, TRUE, 1),
('10A', 'ECONOMY', 150.0, TRUE, 1),
('10B', 'ECONOMY', 150.0, TRUE, 1);

-- 航班 FA234 的座位
INSERT INTO flight_seats (seat_number, seat_type, price, is_available, flight_id)
VALUES
('1B', 'BUSINESS', 300.0, TRUE, 2),
('2B', 'BUSINESS', 300.0, TRUE, 2),
('15A', 'ECONOMY', 120.0, TRUE, 2),
('15B', 'ECONOMY', 120.0, TRUE, 2);

-- 航班 FA345 的座位
INSERT INTO flight_seats (seat_number, seat_type, price, is_available, flight_id)
VALUES
('1C', 'FIRST_CLASS', 800.0, TRUE, 3),
('2C', 'FIRST_CLASS', 800.0, TRUE, 3),
('20A', 'ECONOMY', 200.0, TRUE, 3),
('20B', 'ECONOMY', 200.0, TRUE, 3);

-- 插入示例预订数据
INSERT INTO booking (user_id, flight_id, reference, status, booking_time, total_price)
VALUES (1, 1, 'REF123', 'CONFIRMED', '2023-12-31 12:00:00', 500.0);