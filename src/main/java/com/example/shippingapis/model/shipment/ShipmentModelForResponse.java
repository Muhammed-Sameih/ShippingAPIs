package com.example.shippingapis.model.shipment;

import com.example.shippingapis.entity.Store;
import com.example.shippingapis.model.shipmentItem.ShipmentItemModel;
import com.example.shippingapis.model.store.StoreModelForResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentModelForResponse {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime shipmentDate;
    private String customerEmail;
    private String status;
    private String shippingLocation;
    private String orderCode;
    private StoreModelForResponse store;
    private List<ShipmentItemModel> items;
}
