package com.micro.ak.productservice.service;

import com.micro.ak.productservice.dto.ProductRequest;
import com.micro.ak.productservice.dto.ProductResponse;
import com.micro.ak.productservice.model.Product;
import com.micro.ak.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
//to add logs we can use
@Slf4j
public class ProductService {
    //Inject product repo to save data from request body in the db
    //Using constructor injection

    private final ProductRepository productRepository;

    //Instead of writing this every time we can use an injection called @RequiredArgsConstructor
    //This below constructor will get applied at compile time . . so commenting this for now
//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    public void createProduct(ProductRequest productRequest){
        //using builder to build the product object
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                //by calling build it will create an object of type product
                .build();

        productRepository.save(product);
        //Using slf4j we can use place holder to concat instead of wrirint + productId and all
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();

    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
