package com.micro.ak.productservice.controller;


import com.micro.ak.productservice.dto.ProductRequest;
import com.micro.ak.productservice.dto.ProductResponse;
import com.micro.ak.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
////Instead of writing this every time we can use an injection called @RequiredArgsConstructor
//    //This below constructor will get applied at compile time . . so commenting this for now
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

}
