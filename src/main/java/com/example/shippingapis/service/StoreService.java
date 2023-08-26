package com.example.shippingapis.service;

import com.example.shippingapis.entity.Store;
import com.example.shippingapis.model.store.StoreModelForRequest;

public interface StoreService {
    Store create(StoreModelForRequest storeModel);
}
