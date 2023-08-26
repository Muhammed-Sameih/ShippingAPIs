package com.example.shippingapis.model.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreModelForResponse {
    private Long id;
    private String code;
    private String location;
}
