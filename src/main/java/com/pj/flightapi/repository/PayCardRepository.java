package com.pj.flightapi.repository;

import com.pj.flightapi.entity.PayCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayCardRepository extends JpaRepository<PayCard, Long> {

    List<PayCard> findAllByUserId(Long userId);
}
