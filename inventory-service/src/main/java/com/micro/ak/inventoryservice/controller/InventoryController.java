package com.micro.ak.inventoryservice.controller;

import com.micro.ak.inventoryservice.dto.InventoryResponse;
import com.micro.ak.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isinStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }


}
