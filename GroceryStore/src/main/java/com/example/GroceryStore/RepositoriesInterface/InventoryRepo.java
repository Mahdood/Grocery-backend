package com.example.GroceryStore.RepositoriesInterface;

import com.example.GroceryStore.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepo extends JpaRepository<Inventory, Long> {
    List<Inventory> findByQuantity(int quantity);
    Optional<Inventory> findByProductCode(String productCode);
}

