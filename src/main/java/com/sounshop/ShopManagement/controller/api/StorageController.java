package com.sounshop.ShopManagement.controller.api;

import com.sounshop.ShopManagement.dto.StorageDTO;
import com.sounshop.ShopManagement.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @GetMapping
    public ResponseEntity<List<StorageDTO>> getSearchResults(@RequestParam(required = false) String title, @RequestParam(required = false) String category) {
        System.out.println("check /api/storage method: get");
        List<StorageDTO> storageList = storageService.findStorage(title, category);
        return ResponseEntity.ok(storageList);
    }
}
