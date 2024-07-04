package business.feature.impl;

import business.entity.Category;
import business.feature.ICategory;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class CategoryImpl implements ICategory {
    public static List<Category> categoryList = new ArrayList<>();

    //đọc file
    public CategoryImpl() {
        categoryList = IOFile.readFromFile(IOFile.PATH_CATEGORY);
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }

    @Override
    public void addOrUpdate(Category category) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public int findIndexById(Integer id) {
        return 0;
    }
}
