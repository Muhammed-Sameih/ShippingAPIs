package com.example.shippingapis.model.store;

import com.example.shippingapis.model.shipmentItem.ShipmentItemModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreModelForRequest {
    @NotBlank(message = "Store Code is required!")
    private String code;
    @NotBlank(message = "Store location must not be null.")
    private String location;
    @Valid // This annotation ensures cascading validation for nested objects
    private List<ShipmentItemModel> items;
}
