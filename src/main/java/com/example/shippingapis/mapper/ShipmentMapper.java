package com.example.shippingapis.mapper;

import com.example.shippingapis.entity.Shipment;
import com.example.shippingapis.model.shipment.ShipmentModelForResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {
    @Mapping(target = "store", source = "shipment.store")
    ShipmentModelForResponse toModel(Shipment shipment);
}
