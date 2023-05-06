package com.stav.server.controllers;

import com.stav.server.entities.CategoryEntity;
import com.stav.server.exceptions.ServerException;
import com.stav.server.logic.CategoriesLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private CategoriesLogic categoriesLogic;

    @Autowired
    public CategoriesController(CategoriesLogic categoriesLogic){
        this.categoriesLogic = categoriesLogic;
    }

    @PostMapping
    public void createCategory(@RequestBody CategoryEntity category) throws ServerException {
        categoriesLogic.createCategory(category);
    }

    @PutMapping
    public void updateCategory(@RequestBody CategoryEntity category) throws ServerException {
        categoriesLogic.updateCategory(category);
    }

    @GetMapping("{categoryId}")
    public Optional<CategoryEntity> getCategory(@PathVariable("categoryId") long id) throws ServerException {
        return categoriesLogic.getCategory(id);
    }

    @GetMapping("/byCategoryName")
    public CategoryEntity getCategoryByName(@RequestParam("name") String name) throws ServerException {
        return categoriesLogic.getCategoryByName(name);
    }

    public Iterable<CategoryEntity> getAllCategories() throws ServerException {
        return categoriesLogic.getAllCategories();
    }


    @DeleteMapping("{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") long id) throws ServerException {
        categoriesLogic.deleteCategory(id);
    }
}
