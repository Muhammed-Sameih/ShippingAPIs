package com.example.shippingapis.model.shipmentItem;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentItemModel {
    private Long id;
    @NotBlank(message = "Product Code is required!")
    @JsonProperty("product_code")
    private String productCode;
    @NotNull
    @Min(value = 1, message = "Quantity must be a positive value greater than zero.")
    @Positive(message = "Quantity must be a positive value.")
    private Long quantity;
}
