package com.example.shippingapis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SHIPMENT_ITEM")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "product_code")
    private String productCode;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
