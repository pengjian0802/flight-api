package com.pj.flightapi.service;

import com.pj.flightapi.dto.FlightDetailDto;
import com.pj.flightapi.dto.FlightDto;
import com.pj.flightapi.dto.FlightRequest;
import com.pj.flightapi.dto.PayCardDto;

import java.util.List;


public interface PayCardService {

    List<PayCardDto> queryPayCardList(Long userId);

    PayCardDto savePayCard(PayCardDto payCardDto);
}
