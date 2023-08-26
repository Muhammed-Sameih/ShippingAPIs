package com.example.shippingapis.repo;

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

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StoreRepoTest {

        @Autowired
        private TestEntityManager storeManager;

        @Autowired
        private StoreRepo storeRepo;

        private Store expectedStore;

        @BeforeEach
        void setUp() {
                expectedStore = storeManager.merge(new Store(1L, "S-123", "cairo"));
        }

    @Test
    void findByCode_ShouldReturnStore() {
        // act
        Optional<Store> actualStore = storeRepo.findByCode(expectedStore.getCode());

        // assert
        Assertions.assertThat(actualStore).isPresent();
        Assertions.assertThat(actualStore.get().getCode()).isEqualTo(expectedStore.getCode());
    }

    @Test
    void findByCode_ShouldReturnEmptyOptionalIfCodeDoesNotExist() {
        // act
        Optional<Store> actualStore = storeRepo.findByCode("non-existent-code");

        // assert
        Assertions.assertThat(actualStore).isEmpty();
    }
    @Test
    void findByCode_ShouldReturnEmptyOptionalIfCodeIsNull() {
        // act
        Optional<Store> actualStore = storeRepo.findByCode(null);

        // assert
        Assertions.assertThat(actualStore).isEmpty();
    }
}