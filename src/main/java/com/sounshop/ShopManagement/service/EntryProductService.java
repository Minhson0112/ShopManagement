package com.sounshop.ShopManagement.service;

import com.sounshop.ShopManagement.dto.EntryProductDTO;
import com.sounshop.ShopManagement.entity.EntryProduct;
import com.sounshop.ShopManagement.repository.EntryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EntryProductService {
  
    @Autowired
    private EntryProductRepository entryProductRepository;

    public EntryProduct saveEntryProduct(EntryProduct entryProduct) {
        return entryProductRepository.save(entryProduct);
    }

    public List<EntryProductDTO> getAllEntryProducts() {
        return entryProductRepository.findAllEntryProducts();
    }

    public List<EntryProductDTO> searchEntryProducts(String title, LocalDate day, LocalDate dayBefore, LocalDate dayAfter) {
        if (day != null && title == null && dayBefore == null && dayAfter == null) {
            return entryProductRepository.findByEntryDate(day);
        } else if (day != null) {
            return entryProductRepository.findByProductNameContainingAndEntryDate(title, day);
        } else if (dayBefore != null && dayAfter != null) {
            return entryProductRepository.findByProductNameContainingAndEntryDateBetween(title, dayBefore, dayAfter);
        } else if (dayBefore != null) {
            return entryProductRepository.findByProductNameContainingAndEntryDateBefore(title, dayBefore);
        } else if (dayAfter != null) {
            return entryProductRepository.findByProductNameContainingAndEntryDateAfter(title, dayAfter);
        } else {
            return entryProductRepository.findByProductNameContaining(title);
        }
    }
}
