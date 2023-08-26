package com.example.shippingapis.controller;

import com.example.shippingapis.model.shipment.ShipmentModelForRequest;
import com.example.shippingapis.model.shipment.ShipmentModelForResponse;
import com.example.shippingapis.service.ShipmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/shipments")
@Validated
public class ShipmentController {
    @Autowired
    private ShipmentService shipmentService;
    @PostMapping
    public void create(@RequestBody @Valid ShipmentModelForRequest shipmentModel) {shipmentService.createShipments(shipmentModel);}

    @GetMapping("/by-customer-and-date-range")
    public ResponseEntity<List<ShipmentModelForResponse>> getOrdersByCustomerAndDateRange(
            @RequestParam("email") @NotBlank(message = "Customer email is required!") @Email(message = "Customer email is not valid!") String email,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<ShipmentModelForResponse> shipments = shipmentService.findByCustomerAndRange(email, startDate, endDate);
        if (!shipments.isEmpty()) {
            return new ResponseEntity<>(shipments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
