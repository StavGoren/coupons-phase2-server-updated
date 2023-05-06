package com.stav.server.dal;

import com.stav.server.entities.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoriesDal extends CrudRepository<CategoryEntity, Long> {
    @Query("SELECT id FROM CategoryEntity")
    List<Long> findAllIdsOfCategories();

    @Query("SELECT name FROM CategoryEntity")
    List<String> findAllCategoryNames();

    @Query("SELECT name FROM CategoryEntity WHERE name= :categoryName")
    CategoryEntity findCategoryByName(@Param("categoryName") String categoryName);
}
