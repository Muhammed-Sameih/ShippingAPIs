package com.example.shippingapis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "SHIPMENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "shipment_date")
    private LocalDateTime shipmentDate;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "status")
    private String status;

    @Column(name = "shipping_location")
    private String shippingLocation;

    @Column(name = "order_code")
    private String orderCode;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
