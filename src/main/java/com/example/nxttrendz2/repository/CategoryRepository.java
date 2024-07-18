package com.example.nxttrendz2.repository;

import com.example.nxttrendz2.model.Category;
import java.util.ArrayList;

public interface CategoryRepository {
    ArrayList<Category> getCategories();

    Category getCategoryById(int categoryId);

    Category addCategory(Category category);

    Category updateCategory(int categoryId, Category category);

    void deleteCategory(int categoryId);
}