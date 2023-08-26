package com.example.shippingapis.service.impl;

import com.example.shippingapis.entity.Store;
import com.example.shippingapis.mapper.StoreMapper;
import com.example.shippingapis.model.store.StoreModelForRequest;
import com.example.shippingapis.repo.StoreRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StoreServiceImplTest {

    @Mock
    private StoreMapper storeMapper;
    @Mock
    private StoreRepo storeRepo;
    @InjectMocks
    private StoreServiceImpl storeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreate_NewStore() {
        // Arrange
        StoreModelForRequest storeModel = new StoreModelForRequest();
        storeModel.setCode("S-123");
        when(storeRepo.findByCode(storeModel.getCode())).thenReturn(Optional.empty());
        when(storeMapper.toEntity(storeModel)).thenReturn(new Store(1L, "S-123", "Cairo"));

        // Act
        storeService.create(storeModel);

        // Assert
        verify(storeRepo).save(any(Store.class));
    }
    @Test
    void testCreate_ExistingStore() {
        // Arrange
        StoreModelForRequest storeModel = new StoreModelForRequest();
        storeModel.setCode("S-123");
        when(storeRepo.findByCode(storeModel.getCode())).thenReturn(Optional.of(new Store()));

        // Act
        storeService.create(storeModel);

        // Assert
        verify(storeRepo, never()).save(any(Store.class));
    }
}
