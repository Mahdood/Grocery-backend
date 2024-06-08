package com.example.GroceryStore.Service;

import com.example.GroceryStore.Entity.Inventory;
import com.example.GroceryStore.RepositoriesInterface.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;

    public Inventory addItem(Inventory item) {
        return inventoryRepo.save(item);
    }

    public List<Inventory> getAllItems() {
         return inventoryRepo.findAll();
    }

    public List<Inventory> getOutOfStockItems() {
        List<Inventory> li = inventoryRepo.findByQuantity(0);
        return li;
    }

    public Optional<Inventory> getItemByProductCode(String productCode) {
        return inventoryRepo.findByProductCode(productCode);
    }

    public void deleteItem(Long id) {
        inventoryRepo.deleteById(id);
    }

    public Optional<Inventory> updateItem(Long id, Inventory newItem) {
        return inventoryRepo.findById(id).map(item -> {
            item.setProductCode(newItem.getProductCode());
            item.setDescription(newItem.getDescription());
            item.setQuantity(newItem.getQuantity());
            if(item.getQuantity() == 0){
                item.setStatus("Out Of Stock");
            }
            else{
                item.setStatus("In Stock");
            }
            return inventoryRepo.save(item);
        });
    }

}
