package com.pj.flightapi.repository;

import com.pj.flightapi.entity.Flight;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {

    default List<Flight> findAllByCondition(
            String departureCity,
            String arrivalCity,
            LocalDate departureDate,
            Integer passengers) {

        // 创建一个PageRequest对象，限制返回20条记录，按departureTime升序排列
        Pageable pageable = PageRequest.of(0, 10, Sort.by("departureTime").ascending());

        return findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 动态添加 departureCity 条件
            if (departureCity != null && !departureCity.isEmpty()) {
                predicates.add(cb.equal(root.get("departureCity"), departureCity));
            }
            // 动态添加 arrivalCity 条件
            if (arrivalCity != null && !arrivalCity.isEmpty()) {
                predicates.add(cb.equal(root.get("arrivalCity"), arrivalCity));
            }
            // 动态添加 departureDate 条件
            if (departureDate != null) {
                LocalDateTime startOfDay = departureDate.atStartOfDay();
                LocalDateTime endOfDay = departureDate.plusDays(1).atStartOfDay();
                predicates.add(cb.between(root.get("departureTime"), startOfDay, endOfDay));
            }
            // 动态添加 passengers 条件
            if (passengers != null && passengers > 0) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("availableSeats"), passengers));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable).getContent(); // 获取分页结果中的内容列表
    }
}
