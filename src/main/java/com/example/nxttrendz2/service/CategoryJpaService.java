package com.example.nxttrendz2.service;

import com.example.nxttrendz2.model.Category;
import com.example.nxttrendz2.repository.CategoryJpaRepository;
import com.example.nxttrendz2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryJpaService implements CategoryRepository {
   @Autowired
   private CategoryJpaRepository categoryJpaRepository;

   @Override
   public ArrayList<Category> getCategories() {
      List<Category> categoriesList = categoryJpaRepository.findAll();
      ArrayList<Category> categories = new ArrayList<>(categoriesList);
      return categories;
   }

   @Override
   public Category getCategoryById(int categoryId) {
      try {
         Category category = categoryJpaRepository.findById(categoryId).get();
         return category;
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
   }

   @Override
   public Category addCategory(Category category) {
      categoryJpaRepository.save(category);
      return category;
   }

   @Override
   public Category updateCategory(int categoryId, Category category) {
      try {
         Category newCategory = categoryJpaRepository.findById(categoryId).get();

         if (category.getName() != null) {
            newCategory.setName(category.getName());
         }
         if (category.getDescription() != null) {
            newCategory.setDescription(category.getDescription());
         }
         categoryJpaRepository.save(newCategory);
         return newCategory;
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
   }

   @Override
   public void deleteCategory(int categoryId) {
      try {
         categoryJpaRepository.deleteById(categoryId);
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
   }
}
