package com.example.nxttrendz2.controller;

import com.example.nxttrendz2.model.Category;
import com.example.nxttrendz2.model.Product;
import com.example.nxttrendz2.service.ProductJpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class ProductController {
    @Autowired
    private ProductJpaService productJpaService;

    @GetMapping("/categories/products")
    public ArrayList<Product> getProducts() {
        return productJpaService.getProducts();
    }

    @GetMapping("/categories/products/{productId}")
    public Product getProductById(@PathVariable("productId") int productId) {
        return productJpaService.getProductById(productId);
    }

    @PostMapping("/categories/products")
    public Product addProduct(@RequestBody Product product) {
        return productJpaService.addProduct(product);
    }

    @PutMapping("/categories/products/{productId}")
    public Product updateProduct(@PathVariable("productId") int productId, @RequestBody Product product) {
        return productJpaService.updateProduct(productId, product);
    }

    @DeleteMapping("/categories/products/{productId}")
    public void deleteProduct(@PathVariable("productId") int productId) {
        productJpaService.deleteProduct(productId);
    }

    @GetMapping("/products/{productId}/category")
    public Category getProductCategory(@PathVariable("productId") int productId) {
        return productJpaService.getProductCategory(productId);
    }
}