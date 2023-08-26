package com.example.shippingapis.model.shipment;

import com.example.shippingapis.model.store.StoreModelForRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentModelForRequest {
    @NotBlank(message = "Order Code must not be null.")
    private String orderCode;
    @NotBlank(message = "Customer Email must not be null.")
    private String customerEmail;
    @NotBlank(message = "Customer location must not be null.")
    private String CustomerLocation;
    @NotNull
    @Valid // This annotation ensures cascading validation for nested objects
    private List<StoreModelForRequest> storesInfo;
}
