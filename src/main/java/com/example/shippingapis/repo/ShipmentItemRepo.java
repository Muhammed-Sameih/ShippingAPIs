package com.example.shippingapis.repo;

import com.example.shippingapis.entity.Shipment;
import com.example.shippingapis.entity.ShipmentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShipmentItemRepo extends JpaRepository<ShipmentItem, Long> {
    List<ShipmentItem> findByShipment(Shipment shipment);
}
