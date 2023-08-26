package com.example.shippingapis.repo;


import com.example.shippingapis.entity.Shipment;
import com.example.shippingapis.entity.ShipmentItem;
import com.example.shippingapis.entity.Store;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ShipmentItemRepoTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ShipmentItemRepo shipmentItemRepo;
    private Store store;
    private Shipment shipment;
    @BeforeEach
    public void setUp() {
        store = entityManager.merge(new Store(1L,
                "S-123",
                "cairo"));
        shipment = entityManager.merge(new Shipment(LocalDateTime.now(),
                "user@example.com",
                "CREATED",
                "cairo",
                "O-127",
                store));
        entityManager.merge(new ShipmentItem(1L,
                5L,
                "P-121",
                shipment,
                store));
        entityManager.merge(new ShipmentItem(2L,
                4L,
                "P-122",
                shipment,
                store));
        entityManager.merge(new ShipmentItem(3L,
                3L,
                "P-123",
                shipment,
                store));
    }

    @Test
    void findByShipment_ShouldReturnListOfShipmentItems() {
        // act
        List<ShipmentItem> actualList = shipmentItemRepo.findByShipment(shipment);

        // assert
        List<String> actualProductCodes = actualList.stream()
                .map(ShipmentItem::getProductCode)
                .collect(Collectors.toList());

        Assertions.assertThat(actualProductCodes)
                .contains("P-121", "P-122", "P-123")
                .doesNotContain("P-124", "P-125");
    }

    @Test
    void findByShipment_ShouldReturnEmptyShipmentItemIfShipmentDoesNotExist() {
        // arrange
        Shipment invalidShipment = entityManager.persist(new Shipment(LocalDateTime.now(), "xUser@example.com", "CREATED", "Alex", "O-127", store));

        // act
        List<ShipmentItem> actualList = shipmentItemRepo.findByShipment(invalidShipment);

        // assert
        Assertions.assertThat(actualList).isEmpty();
    }

    @Test
    void findByShipment_ShouldReturnEmptyShipmentItemListIfShipmentIsNull() {
        // act & assert
        Assertions.assertThat(this.shipmentItemRepo.findByShipment(null)).isEmpty();
    }
}
