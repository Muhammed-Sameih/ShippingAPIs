package com.example.shippingapis.repo;

import com.example.shippingapis.entity.Shipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.assertj.core.api.Assertions;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ShipmentRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShipmentRepo shipmentRepo;

    private Shipment shipment1;
    private Shipment shipment2;
    private Shipment shipment3;

    @BeforeEach
    void setUp() {
        this.shipment1 = entityManager.merge(new Shipment(null,now(),
                "user1@example.com",
                "CREATED",
                "cairo",
                "O-123",
                null));

        this.shipment2= entityManager.merge(new Shipment(null,now(),
                "user2@example.com",
                "SHIPPED",
                "cairo",
                "O-124",
                null));
        this.shipment3 = entityManager.merge(new Shipment(null,now(),
                "user1@example.com",
                "DELIVERED",
                "cairo",
                "O-125", null));
    }

    @Test
    void findByCustomerEmailAndShipmentDateBetween_ShouldReturnMatchingShipments() {
        // Arrange
        LocalDateTime startDate = now().minusDays(1);
        LocalDateTime endDate = now().plusDays(1);

        // Act
        List<Shipment> actualList = shipmentRepo.findByCustomerEmailAndShipmentDateBetween("user1@example.com", startDate, endDate);

        // Assert
        Assertions.assertThat(actualList).containsExactlyInAnyOrder(shipment1, shipment3);
    }

    @Test
    void findByCustomerEmailAndShipmentDateBetween_ShouldReturnEmptyListIfNoMatches() {
        // Arrange
        LocalDateTime startDate = now().minusDays(1);
        LocalDateTime endDate = now().minusHours(1);

        // Act
        List<Shipment> actualList = shipmentRepo.findByCustomerEmailAndShipmentDateBetween("user2@example.com", startDate, endDate);

        // Assert
        Assertions.assertThat(actualList).isEmpty();
    }
    @Test
    void findByCustomerEmailAndShipmentDateBetween_ShouldReturnEmptyListIfCustomerEmailIsNull() {
        // Arrange
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);

        // Act
        List<Shipment> actualList = shipmentRepo.findByCustomerEmailAndShipmentDateBetween(null, startDate, endDate);

        // Assert
        Assertions.assertThat(actualList).isEmpty();
    }

    @Test
    void findByCustomerEmailAndShipmentDateBetween_ShouldReturnEmptyListIfStartDateIsNull() {
        // Act
        List<Shipment> actualList = shipmentRepo.findByCustomerEmailAndShipmentDateBetween("user1@example.com", null, LocalDateTime.now());

        // Assert
        Assertions.assertThat(actualList).isEmpty();
    }

    @Test
    void findByCustomerEmailAndShipmentDateBetween_ShouldReturnEmptyListIfEndDateIsNull() {
        // Act
        List<Shipment> actualList = shipmentRepo.findByCustomerEmailAndShipmentDateBetween("user1@example.com", LocalDateTime.now().minusDays(1), null);

        // Assert
        Assertions.assertThat(actualList).isEmpty();
    }
}
