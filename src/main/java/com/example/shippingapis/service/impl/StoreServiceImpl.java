package com.example.shippingapis.service.impl;

import com.example.shippingapis.entity.Store;
import com.example.shippingapis.mapper.StoreMapper;
import com.example.shippingapis.model.store.StoreModelForRequest;
import com.example.shippingapis.repo.StoreRepo;
import com.example.shippingapis.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired private StoreMapper storeMapper;
    @Autowired private StoreRepo storeRepo;
    @Override
    public Store create(StoreModelForRequest storeModel) {
        Optional<Store> existingCoupon = storeRepo.findByCode(storeModel.getCode());
        return existingCoupon.orElseGet(() -> this.createStore(storeModel));
    }
    private Store createStore(StoreModelForRequest storeModel) {
        return storeRepo.save(storeMapper.toEntity(storeModel));
    }
}
