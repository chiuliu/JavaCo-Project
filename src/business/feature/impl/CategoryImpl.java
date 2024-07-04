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
        return categoryList;
    }

    @Override
    public void addOrUpdate(Category category) {
        int index = findIndexById(category.getCategoryId());
        if(index >= 0){
            categoryList.set(index, category);
        }
        else {
            categoryList.add(category);
        }
        IOFile.writeToFile(IOFile.PATH_CATEGORY, categoryList);

    }

    @Override
    public void delete(Integer id) {
        int index = findIndexById(id);
        if(index >= 0){
            boolean isExist = true;
//            for(Product p : )
        }

    }

    @Override
    public int findIndexById(Integer id) {
        return 0;
    }
}
