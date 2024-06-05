package com.example.orderItems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orderItems.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
    
}
