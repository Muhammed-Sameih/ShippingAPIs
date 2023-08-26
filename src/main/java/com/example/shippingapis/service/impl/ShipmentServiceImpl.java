package com.example.shippingapis.service.impl;

import com.example.shippingapis.entity.Shipment;
import com.example.shippingapis.entity.Store;
import com.example.shippingapis.mapper.ShipmentMapper;
import com.example.shippingapis.model.shipment.ShipmentModelForRequest;
import com.example.shippingapis.model.shipment.ShipmentModelForResponse;
import com.example.shippingapis.repo.ShipmentRepo;
import com.example.shippingapis.service.ShipmentItemService;
import com.example.shippingapis.service.ShipmentService;
import com.example.shippingapis.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipmentServiceImpl implements ShipmentService {
    @Autowired
    private ShipmentRepo shipmentRepo;
    @Autowired
    private StoreService storeService;
    @Autowired
    private ShipmentItemService shipmentItemService;
    @Autowired
    private ShipmentMapper shipmentMapper;

    @Override
    public void createShipments(ShipmentModelForRequest shipmentModel) {
        shipmentModel.getStoresInfo()
                .stream()
                .forEach(storeModel -> {
                    Store store = storeService.create(storeModel);
                    Shipment shipment = new Shipment(LocalDateTime.now(),shipmentModel.getCustomerEmail(),"SHIPPED",shipmentModel.getCustomerLocation(),shipmentModel.getOrderCode(),store);
                    shipment = shipmentRepo.save(shipment);
                    shipmentItemService.create(storeModel.getItems(),shipment,store);
                });
    }
    @Override
    public List<ShipmentModelForResponse> findByCustomerAndRange(String customerEmail, LocalDateTime startDate, LocalDateTime endDate) {
        List<Shipment> shipments = shipmentRepo.findByCustomerEmailAndShipmentDateBetween(customerEmail, startDate, endDate);
        return shipments
                .stream()
                .map(shipment -> {
                    ShipmentModelForResponse shipmentModel = shipmentMapper.toModel(shipment);
                    shipmentModel.setItems(shipmentItemService.findShipmentItemsByShipment(shipment));
                    return shipmentModel;
                })
                .collect(Collectors.toList());

    }
}
