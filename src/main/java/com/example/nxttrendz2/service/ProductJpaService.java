package com.example.nxttrendz2.service;

import com.example.nxttrendz2.model.Category;

import com.example.nxttrendz2.model.Product;
import com.example.nxttrendz2.repository.ProductJpaRepository;
import com.example.nxttrendz2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductJpaService implements ProductRepository {
     @Autowired
     private ProductJpaRepository productJpaRepository;
     @Autowired
     private CategoryJpaRepository categoryJpaRepository;

     @Override
     public ArrayList<Product> getProducts() {
          List<Product> productList = productJpaRepository.findAll();
          ArrayList<Product> products = new ArrayList<>(productList);
          return products;
     }

     @Override
     public Product getProductById(int productId){
      try{
           Product product=productJpaRepository.findById(productId).get();
           return product;
      }catch(Exception e){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
      
     }

     @Override
     public Product addProduct(Product product) {
          int categoryId = product.getCategory().getId();
          Category category = categoryJpaRepository.getCategoryById(categoryId);
          product.setCategory(category);
          productJpaRepository.save(product);
          return product;

     }

     @Override
     public Product updateProduct(int productId, Product product) {
          try {

               Product newProduct = productJpaRepository.findById(productId).get();
               if (product.getCategory() != null) {
                    int categoryId = product.getCategory().getId();
                    Category newCategory = categoryJpaRepository.getCategoryById(categoryId);
                    newProduct.setCategory(newCategory);

               }
               if (product.getName() != null) {
                    newProduct.setName(product.getName());
               }
               if (product.getDescription() != null) {
                    newProduct.setDescription(product.getDescription());
               }
               if (product.getPrice() != null) {
                    newProduct.setPrice(product.getPrice());
               }
               productJpaRepository.save(newProduct);
               return newProduct;
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND);
          }
     }

     @Override
     public void deleteProduct(int productId) {
          try {
               productJpaRepository.deleteById(productId);
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND);
          }
     }

     @Override
     public Category getProductCategory(int productId) {
          try {
               Product product = productJpaRepository.findById(productId).get();
               Category category = product.getCategory();
               return category;
          } catch (Exception e) {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND);
          }
     }
}
