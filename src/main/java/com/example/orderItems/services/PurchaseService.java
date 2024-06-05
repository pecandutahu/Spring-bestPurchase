package com.example.orderItems.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.orderItems.models.Item;
import com.example.orderItems.repositories.ItemRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> calculateBestPurchase(int budget) {
        List<Item> items = itemRepository.findAll(); // untuk menampung semua item
        List<Item> result = new ArrayList<>(); // inisiasi result untuk menampung kombinasi terbaik sesuai budget
        calculate(items, budget, new ArrayList<>(), result); // panggil fungsi untuk menghitung budget
        return result;
    }

    /**
     * 
     * @param items List semua item dambil dari repo
     * @param budget budget sesuai yang diinput user
     * @param current untuk menampung daftar item yang sedang dibandingkan
     * @param result untuk menampung kombinasi 
     */
    private void calculate(List<Item> items, int budget, List<Item> current, List<Item> result) {
        int currentSum = current.stream().mapToInt(Item::getPrice).sum(); // jumlah price dari item yang ada pada variable current (variable ini akan bergerak)
        int resultSum = result.stream().mapToInt(Item::getPrice).sum(); // Jumlah price dari result, result ini akan terus bergerak

        // Jika total kombinasi saat ini (curentSum) lebih kecil dari budget dan lebih besar dari kombinasi yang sudah ditampung, 
        // maka update result dengan kombinasi saat ini
        if (currentSum <= budget && currentSum > resultSum) {
            result.clear();
            result.addAll(new ArrayList<>(current));
        }

        for (int i = 0; i < items.size(); i++) { //Lakukan iterasi sebanyak item
            List<Item> remaining = new ArrayList<>(items.subList(i + 1, items.size()));//tampung data yang belum dibandingkan mulai dari item dengan index plus 1
            List<Item> combination = new ArrayList<>(current); //definisikan nilai kombinasi
            combination.add(items.get(i)); //set nilai kombinasi
            calculate(remaining, budget, combination, result); //panggil lagi metode calculate secara rekursif dengan daftar parameter yang telah diperbaharui
        }
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}

