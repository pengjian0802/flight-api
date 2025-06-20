package com.pj.flightapi.repository;

import com.pj.flightapi.entity.Booking;
import com.pj.flightapi.entity.Flight;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {

    default List<Booking> findAllByCondition(Long userId) {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bookingTime").ascending());

        return findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("userId"), userId));

            // 动态添加条件

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable).getContent();
    }
}
