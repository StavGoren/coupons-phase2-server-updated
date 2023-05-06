package com.stav.server.logic;

import com.stav.server.dal.ICategoriesDal;
import com.stav.server.entities.CategoryEntity;
import com.stav.server.enums.ErrorType;
import com.stav.server.exceptions.ServerException;
import com.stav.server.utils.ValidationsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesLogic {
    private ICategoriesDal iCategoriesDal;

    @Autowired
    public CategoriesLogic(ICategoriesDal iCategoriesDal) {
        this.iCategoriesDal = iCategoriesDal;
    }


    /*******************************
     Public methods and actions
     *******************************/

    public long createCategory(CategoryEntity category) throws ServerException {
        ValidationsUtils.validateNameLength(category.getName());
        iCategoriesDal.save(category);
        return category.getId();
    }

    public void updateCategory(CategoryEntity category) throws ServerException {
        ValidationsUtils.validateNameLength(category.getName());
        iCategoriesDal.save(category);
    }

    public Optional<CategoryEntity> getCategory(long id) throws ServerException {
        isIdExist(id);
        return iCategoriesDal.findById(id);
    }

    public CategoryEntity getCategoryByName(String name) throws ServerException {
        isNameExist(name);
        return iCategoriesDal.findCategoryByName(name);
    }

    public void deleteCategory(long id) throws ServerException {
        isIdExist(id);
        iCategoriesDal.deleteById(id);
    }

    public Iterable<CategoryEntity> getAllCategories() throws ServerException {
        return iCategoriesDal.findAll();
    }


    /*******************************
     ValidationsUtils
     *******************************/

    private boolean isIdExist(long categoryId) throws ServerException {
//        ValidationsUtils.validateNumber(categoryId);

        List<Long> categoriesIdList = iCategoriesDal.findAllIdsOfCategories();
        for (long id : categoriesIdList) {
            if (id == categoryId) {
                return true;
            }
        }
        throw new ServerException(ErrorType.DATA_NOT_FOUND, " category with ID " + categoryId + " does not exist in our data base");
    }

    protected boolean isNameExist(String categoryName) throws ServerException {
//        Checking if category exists in DB
        List<String> categoryNameList = iCategoriesDal.findAllCategoryNames();
        for (int i = 0; i < categoryNameList.size(); i++) {
            if (categoryNameList.get(i) == categoryName) {
                return true;
            }
        }
        throw new ServerException(ErrorType.DATA_NOT_FOUND, " category " + categoryName + " doesn't exist");
    }
}