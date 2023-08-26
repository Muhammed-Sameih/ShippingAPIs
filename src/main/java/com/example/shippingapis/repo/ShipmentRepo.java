package com.example.shippingapis.repo;

import com.example.shippingapis.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShipmentRepo extends JpaRepository<Shipment, Long> {
    List<Shipment> findByCustomerEmailAndShipmentDateBetween(String customerEmail, LocalDateTime startDate, LocalDateTime endDate);
}
