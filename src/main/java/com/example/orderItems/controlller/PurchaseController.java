package com.example.orderItems.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.orderItems.models.Item;
import com.example.orderItems.services.PurchaseService;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<List<Item>> getBestPurchase(@RequestParam int budget) {
        List<Item> bestPurchase = purchaseService.calculateBestPurchase(budget);
        return new ResponseEntity<>(bestPurchase, HttpStatus.OK);
    }

    @PostMapping("/items")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item newItem = purchaseService.addItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = purchaseService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
