package com.sounshop.ShopManagement.service;

import com.sounshop.ShopManagement.dto.StorageDTO;
import com.sounshop.ShopManagement.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService {
    @Autowired
    private StorageRepository storageRepository;

    // Lấy tất cả thông tin sản phẩm từ kho
    public List<StorageDTO> getAllStorage() {
        return storageRepository.findAllStorageDTOs();
    }

    // Tìm kiếm sản phẩm trong kho theo tên và danh mục
    public List<StorageDTO> findStorage(String title, String category) {
        if (title != null && category != null) {
            return storageRepository.findByProductNameContainingAndCategory(title, category);
        } else if (title != null) {
            return storageRepository.findByProductNameContaining(title);
        } else if (category != null) {
            return storageRepository.findByCategory(category);
        } else {
            return getAllStorage();
        }
    }
}
