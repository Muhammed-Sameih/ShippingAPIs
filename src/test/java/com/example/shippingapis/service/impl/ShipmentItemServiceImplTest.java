package com.example.shippingapis.service.impl;

import com.example.shippingapis.entity.Shipment;
import com.example.shippingapis.entity.ShipmentItem;
import com.example.shippingapis.entity.Store;
import com.example.shippingapis.mapper.ShipmentItemMapper;
import com.example.shippingapis.model.shipmentItem.ShipmentItemModel;
import com.example.shippingapis.repo.ShipmentItemRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShipmentItemServiceImplTest {

    @InjectMocks
    private ShipmentItemServiceImpl shipmentItemService;
    @Mock
    private ShipmentItemRepo shipmentItemRepo;
    @Mock
    private ShipmentItemMapper shipmentItemMapper;
    private ShipmentItemModel firItemModel;
    private ShipmentItemModel secItemModel;
    private Shipment shipment;
    private Store store;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        firItemModel = new ShipmentItemModel(1L,
                "P-121",
                5L);
        secItemModel = new ShipmentItemModel(2L,
                "P-122",
                4L);
        store = new Store(1L,
                "S-123",
                "cairo");
        shipment = new Shipment(1L,
                LocalDateTime.now(),
                "user@example.com",
                "CREATED",
                "cairo",
                "O-127",
                store);
    }

    @Test
    void Create_ShouldSaveAllItems() {
        // Arrange
        List<ShipmentItemModel> itemModels = Arrays.asList(firItemModel, secItemModel);
        ShipmentItem firItem = new ShipmentItem(1L, 5L, "P-121", shipment, store);
        ShipmentItem secItem = new ShipmentItem(2L, 4L, "P-122", shipment, store);
        when(shipmentItemMapper.toEntity(any(ShipmentItemModel.class)))
                .thenReturn(firItem, secItem);

        ArgumentCaptor<List<ShipmentItem>> captor = ArgumentCaptor.forClass(List.class);

        // Act
        shipmentItemService.create(itemModels, shipment, store);

        // Assert
        verify(shipmentItemRepo).saveAll(captor.capture());

        List<ShipmentItem> savedItems = captor.getValue();
        assertEquals(2, savedItems.size());
        assertTrue(savedItems.contains(firItem));
        assertTrue(savedItems.contains(secItem));
    }

    @Test
    void FindShipmentItemsByShipment_ShouldReturnMappedModels() {
        // Arrange
        List<ShipmentItem> items = Arrays.asList(new ShipmentItem(2L, 4L, "P-122", shipment, store));
        when(shipmentItemRepo.findByShipment(any(Shipment.class))).thenReturn(items);

        ShipmentItemModel mappedModel = new ShipmentItemModel(2L, "P-122", 4L);
        when(shipmentItemMapper.toModel(any(ShipmentItem.class))).thenReturn(mappedModel);

        // Act
        List<ShipmentItemModel> actualModels = shipmentItemService.findShipmentItemsByShipment(shipment);

        // Assert
        verify(shipmentItemRepo).findByShipment(eq(shipment));
        verify(shipmentItemMapper).toModel(eq(items.get(0)));
        assertEquals(1, actualModels.size());
        ShipmentItemModel resultItem = actualModels.get(0);
        assertEquals(mappedModel.getId(), resultItem.getId());
        assertEquals(mappedModel.getProductCode(), resultItem.getProductCode());
        assertEquals(mappedModel.getQuantity(), resultItem.getQuantity());
    }
    @Test
    void FindShipmentItemsByShipment_NoItemsFound() {
        // Arrange
        when(shipmentItemRepo.findByShipment(any(Shipment.class))).thenReturn(Collections.emptyList());

        // Act
        List<ShipmentItemModel> result = shipmentItemService.findShipmentItemsByShipment(shipment);

        // Assert
        assertTrue(result.isEmpty());
        verify(shipmentItemRepo).findByShipment(eq(shipment));
        verify(shipmentItemMapper, never()).toModel(any(ShipmentItem.class));
    }
}
