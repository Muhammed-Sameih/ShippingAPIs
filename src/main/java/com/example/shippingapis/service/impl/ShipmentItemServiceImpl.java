package com.example.shippingapis.service.impl;

import com.example.shippingapis.entity.Shipment;
import com.example.shippingapis.entity.ShipmentItem;
import com.example.shippingapis.entity.Store;
import com.example.shippingapis.mapper.ShipmentItemMapper;
import com.example.shippingapis.model.shipmentItem.ShipmentItemModel;
import com.example.shippingapis.repo.ShipmentItemRepo;
import com.example.shippingapis.service.ShipmentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipmentItemServiceImpl implements ShipmentItemService {
    @Autowired
    private ShipmentItemRepo shipmentItemRepo;
    @Autowired
    private ShipmentItemMapper shipmentItemMapper;
    @Override
    public List<ShipmentItem> create(List<ShipmentItemModel> itemModels, Shipment shipment, Store store) {
        List<ShipmentItem> items = itemModels.stream()
                .map(shipmentItemMapper::toEntity)
                .map(itemModel -> {
                    itemModel.setShipment(shipment);
                    itemModel.setStore(store);
                    return itemModel;
                })
                .collect(Collectors.toList());
        return shipmentItemRepo.saveAll(items);
    }
   @Override
    public List<ShipmentItemModel> findShipmentItemsByShipment(Shipment shipment) {
        return shipmentItemRepo.findByShipment(shipment).stream()
                .map(shipmentItemMapper::toModel)
                .collect(Collectors.toList());
    }
}
