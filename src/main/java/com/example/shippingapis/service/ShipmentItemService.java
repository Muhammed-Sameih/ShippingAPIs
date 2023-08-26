package com.example.shippingapis.service;

import com.example.shippingapis.entity.Shipment;
import com.example.shippingapis.entity.ShipmentItem;
import com.example.shippingapis.entity.Store;
import com.example.shippingapis.model.shipmentItem.ShipmentItemModel;

import java.util.List;

public interface ShipmentItemService {
    List<ShipmentItem> create(List<ShipmentItemModel> items, Shipment shipment, Store store);
    List<ShipmentItemModel> findShipmentItemsByShipment(Shipment shipment);
}
