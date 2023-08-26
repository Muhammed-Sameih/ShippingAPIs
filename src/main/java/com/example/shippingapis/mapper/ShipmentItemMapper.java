package com.example.shippingapis.mapper;

import com.example.shippingapis.entity.ShipmentItem;
import com.example.shippingapis.model.shipmentItem.ShipmentItemModel;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShipmentItemMapper {
    @Mapping(target = "productCode", source = "productCode")
    @Mapping(target = "quantity", source = "quantity")
    ShipmentItem toEntity(@Valid ShipmentItemModel shipmentItemModel);
    @Mapping(target = "productCode", source = "productCode")
    @Mapping(target = "quantity", source = "quantity")
    ShipmentItemModel toModel(ShipmentItem shipmentItem);
}
