package com.example.GroceryStore.Controller;

import com.example.GroceryStore.Entity.Inventory;
import com.example.GroceryStore.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grocery")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    //Api to insert inventory of products [which can include their quantity and description]
    @PostMapping("/insertInventory")
    public Inventory addItem(@RequestBody Inventory item) {
        return inventoryService.addItem(item);
    }

    //Api to fetch all present inventory along with their status [in/out of stock]
    @GetMapping("/getAllStockItems")
    public List<Inventory> getAllItems() {
        return inventoryService.getAllItems();
    }

    //Api to fetch all out of stock inventory
    @GetMapping("/getOutOfStockItems")
    public List<Inventory> getOutOfStockItems() {
        return inventoryService.getOutOfStockItems();
    }

    //Api to edit inventory
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateItem(@PathVariable Long id, @RequestBody Inventory newItem) {
        return inventoryService.updateItem(id, newItem)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Api to delete inventory
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    //Api to search inventory by given code.
    @GetMapping("/{productCode}")
    public ResponseEntity<Inventory> getItemByProductCode(@PathVariable String productCode) {
        return inventoryService.getItemByProductCode(productCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
