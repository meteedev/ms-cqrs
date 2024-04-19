package com.learn.cqrs.service.impl;


import com.learn.cqrs.dto.PurchaseOrderSummaryDto;
import com.learn.cqrs.entity.PurchaseOrderSummary;
import com.learn.cqrs.repository.PurchaseOrderSummaryRepository;
import com.learn.cqrs.service.OrderQueryService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {


    private PurchaseOrderSummaryRepository purchaseOrderSummaryRepository;

    public OrderQueryServiceImpl(PurchaseOrderSummaryRepository purchaseOrderSummaryRepository) {
        this.purchaseOrderSummaryRepository = purchaseOrderSummaryRepository;
    }

    @Override
    public List<PurchaseOrderSummaryDto> getSaleSummaryGroupByState() {
        return this.purchaseOrderSummaryRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseOrderSummaryDto getSaleSummaryByState(String state) {
        return this.purchaseOrderSummaryRepository.findByState(state)
                        .map(this::entityToDto)
                        .orElseGet(() -> new PurchaseOrderSummaryDto(state, 0));
    }

    @Override
    public double getTotalSale() {
        return this.purchaseOrderSummaryRepository.findAll()
                        .stream()
                        .mapToDouble(PurchaseOrderSummary::getTotalSale)
                        .sum();
    }

    private PurchaseOrderSummaryDto entityToDto(PurchaseOrderSummary purchaseOrderSummary){
        PurchaseOrderSummaryDto dto = new PurchaseOrderSummaryDto();
        dto.setState(purchaseOrderSummary.getState());
        dto.setTotalSale(purchaseOrderSummary.getTotalSale());
        return dto;
    }
}
