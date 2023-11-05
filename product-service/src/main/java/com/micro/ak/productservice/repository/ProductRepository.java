package com.micro.ak.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.micro.ak.productservice.model.Product;
//We will extend this with mongodb interface repo generic argument will be product
//FOr identfiier I am going to pass string as second generic argument
//String because it is the type of id field in our product class
public interface ProductRepository extends MongoRepository<Product, String> {

}
