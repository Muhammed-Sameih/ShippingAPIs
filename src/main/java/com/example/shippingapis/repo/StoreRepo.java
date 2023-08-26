package com.example.shippingapis.repo;

import com.example.shippingapis.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long> {
    Optional<Store> findByCode(String code);
}
