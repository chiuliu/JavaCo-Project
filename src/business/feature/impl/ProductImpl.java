package business.feature.impl;

import business.entity.Product;
import business.feature.IProduct;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements IProduct {
    public static  List<Product> productList = new ArrayList<Product>();
    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void addOrUpdate(Product product) {
        int index = findIndexById(product.getProductId());
        if (index >= 0) {
            productList.set(index, product);
        } else {
            productList.add(product);
        }
        IOFile.writeToFile(IOFile.PATH_PRODUCT,productList);


    }

    @Override
    public void delete(Integer id) {
        int index = findIndexById(id);
        if (index >= 0) {
            productList.remove(index);
            System.out.println("Xóa sản phẩm thành công ");
        } else {
            System.err.println("Mã sản phẩm không tồn tại");
        }
        IOFile.writeToFile(IOFile.PATH_PRODUCT,productList);

    }

    @Override
    public int findIndexById(Integer id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId() == id) {
                return i;
            }
        }
        return -1;
    }

}

