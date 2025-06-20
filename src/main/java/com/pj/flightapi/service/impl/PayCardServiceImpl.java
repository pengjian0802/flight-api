package com.pj.flightapi.service.impl;

import com.pj.flightapi.dto.PayCardDto;
import com.pj.flightapi.entity.PayCard;
import com.pj.flightapi.repository.PayCardRepository;
import com.pj.flightapi.service.PayCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PayCardServiceImpl implements PayCardService {

    @Autowired
    private PayCardRepository payCardRepository;

    @Override
    public List<PayCardDto> queryPayCardList(Long userId) {
        List<PayCardDto> payCardDtos = new ArrayList<>();
        List<PayCard> payCards = payCardRepository.findAllByUserId(userId);
        if (!CollectionUtils.isEmpty(payCards)) {
            for (PayCard payCard : payCards) {
                PayCardDto payCardDto = new PayCardDto();
                payCardDto.setId(payCard.getId());
                payCardDto.setUserId(payCard.getUserId());
                payCardDto.setCardType(payCard.getCardType());
                payCardDto.setCardNumber(payCard.getCardNumber());
                payCardDto.setCvv(payCard.getCvv());
                payCardDto.setExpireDate(payCard.getExpireDate());
                payCardDto.setCardHolderName(payCard.getCardHolderName());
                payCardDtos.add(payCardDto);
            }
        }
        return payCardDtos;
    }

    @Override
    public PayCardDto savePayCard(PayCardDto payCardDto) {
        PayCard payCard = null;
        if (payCardDto.getId() != null) {
            payCard = payCardRepository.findById(payCardDto.getId()).orElse(null);
        }
        if (payCard == null) {
            payCard = new PayCard();
        }
        payCard.setUserId(payCardDto.getUserId());
        payCard.setCardType(payCardDto.getCardType());
        payCard.setCardNumber(payCardDto.getCardNumber());
        payCard.setCvv(payCardDto.getCvv());
        payCard.setExpireDate(payCardDto.getExpireDate());
        payCard.setCardHolderName(payCardDto.getCardHolderName());
        payCardRepository.save(payCard);
        payCardDto.setId(payCard.getId());
        return payCardDto;
    }
}
