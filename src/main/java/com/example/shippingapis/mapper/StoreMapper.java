package com.example.shippingapis.mapper;

import com.example.shippingapis.entity.Store;
import com.example.shippingapis.model.store.StoreModelForRequest;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    @Mapping(target = "code", source = "code")
    @Mapping(target = "location", source = "location")
    Store toEntity(@Valid StoreModelForRequest storeModel);
}
