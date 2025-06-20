package com.pj.flightapi.controller;

import com.pj.flightapi.common.Result;
import com.pj.flightapi.dto.PayCardDto;
import com.pj.flightapi.service.PayCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payCard")
public class PayCardController {

    @Autowired
    private PayCardService payCardService;

    @PostMapping("/save")
    public Result<PayCardDto> saveNewPayCard(@RequestBody PayCardDto payCardDto) {
        PayCardDto cardDto = payCardService.savePayCard(payCardDto);
        return Result.success(cardDto);
    }

    @GetMapping("/list/{userId}")
    public Result<List<PayCardDto>> getFlightDetail(@PathVariable Long userId) {
        List<PayCardDto> payCardDtos = payCardService.queryPayCardList(userId);
        return Result.success(payCardDtos);
    }
}
