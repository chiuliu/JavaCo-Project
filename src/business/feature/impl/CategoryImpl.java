package business.feature.impl;

import business.entity.Category;
import business.entity.Product;
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
        if (index >= 0) {
            categoryList.set(index, category);
        } else {
            categoryList.add(category);
        }
        IOFile.writeToFile(IOFile.PATH_CATEGORY, categoryList);

    }

    @Override
    public void delete(Integer id) {
        int index = findIndexById(id);
        if (index >= 0) {
            boolean isExist = true;
            for (Product p : ProductImpl.productList) {
                if (p.getCategory().getCategoryId() == id) {
                    isExist = false;
                    break;
                }
            }
            if (isExist) {
                categoryList.remove(index);
                System.out.println("Xóa danh mục thành công");
            } else {
                Category category = categoryList.get(index);
                category.setStatus(!category.getStatus());
                addOrUpdate(category);
                System.err.println("Danh mục có chứa sản phẩm, k thể xoá ");
            }


        } else {
            System.err.println("Không tìm thấy mã danh mục trên");
        }
    }


    @Override
    public int findIndexById(Integer id) {

        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getCategoryId() == id) {
                return i;
            }
        }
        return -1;

    }
}
