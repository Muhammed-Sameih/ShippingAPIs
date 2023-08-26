package com.example.shippingapis.service.impl;

import com.example.shippingapis.entity.Shipment;
import com.example.shippingapis.entity.Store;
import com.example.shippingapis.mapper.ShipmentMapper;
import com.example.shippingapis.model.shipment.ShipmentModelForRequest;
import com.example.shippingapis.model.shipment.ShipmentModelForResponse;
import com.example.shippingapis.model.store.StoreModelForRequest;
import com.example.shippingapis.repo.ShipmentRepo;
import com.example.shippingapis.service.ShipmentItemService;
import com.example.shippingapis.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShipmentServiceImplTest {

    @InjectMocks
    private ShipmentServiceImpl shipmentService;
    @Mock
    private ShipmentRepo shipmentRepo;
    @Mock
    private StoreService storeService;
    @Mock
    private ShipmentItemService shipmentItemService;
    @Mock
    private ShipmentMapper shipmentMapper;
    private ShipmentModelForRequest shipmentModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a sample ShipmentModelForRequest for testing
        List<StoreModelForRequest> storeModels = new ArrayList<>();
        storeModels.add(new StoreModelForRequest("S-123", "Cairo", new ArrayList<>()));
        shipmentModel = new ShipmentModelForRequest("O-127", "user@example.com", "cairo", storeModels);
    }
    @Test
    void testCreateShipments() {
        // Arrange
        Store store = new Store(1L, "S-123", "Cairo");
        Shipment shipment = new Shipment(LocalDateTime.now(), "user@example.com", "SHIPPED", "cairo", "O-127", store);

        when(storeService.create(any(StoreModelForRequest.class))).thenReturn(store);
        when(shipmentRepo.save(any(Shipment.class))).thenReturn(shipment);

        // Act
        shipmentService.createShipments(shipmentModel);

        // Assert
        verify(storeService).create(any(StoreModelForRequest.class));
        verify(shipmentRepo).save(any(Shipment.class));
        verify(shipmentItemService).create(anyList(), any(Shipment.class), any(Store.class));
    }
    @Test
    void testFindByCustomerAndRange() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        LocalDateTime endDate = LocalDateTime.now();
        Shipment shipment = new Shipment(1L, LocalDateTime.now(), "user@example.com", "SHIPPED", "cairo", "O-127", new Store());
        ShipmentModelForResponse shipmentModelResponse = new ShipmentModelForResponse();
        shipmentModelResponse.setId(1L);

        when(shipmentRepo.findByCustomerEmailAndShipmentDateBetween(anyString(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList(shipment));
        when(shipmentMapper.toModel(any(Shipment.class))).thenReturn(shipmentModelResponse);
        when(shipmentItemService.findShipmentItemsByShipment(any(Shipment.class))).thenReturn(new ArrayList<>());

        // Act
        List<ShipmentModelForResponse> result = shipmentService.findByCustomerAndRange("user@example.com", startDate, endDate);

        // Assert
        assertEquals(1, result.size());
        assertEquals(shipmentModelResponse, result.get(0));
        verify(shipmentRepo).findByCustomerEmailAndShipmentDateBetween(eq("user@example.com"), eq(startDate), eq(endDate));
        verify(shipmentMapper).toModel(any(Shipment.class));
        verify(shipmentItemService).findShipmentItemsByShipment(any(Shipment.class));
    }
}
