package com.example.shippingapis.service;

import com.example.shippingapis.model.shipment.ShipmentModelForRequest;
import com.example.shippingapis.model.shipment.ShipmentModelForResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface ShipmentService {
    void createShipments(ShipmentModelForRequest shipmentModel);
    List<ShipmentModelForResponse> findByCustomerAndRange(String customerEmail, LocalDateTime startDate, LocalDateTime endDate);

}
