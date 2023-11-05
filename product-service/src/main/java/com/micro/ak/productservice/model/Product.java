package com.micro.ak.productservice.model;


//To define the product as a mongodb document I will add document annotation on top of the produyct class , and lombok annotation to geenrate getter and setter methods
//and cosntructors

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value= "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    //By adding Id annotation I am specifycing that id field is a unique identifier
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}

//To store this product inside db we are going to create spring data repository
//FOr that we will create package called repository


