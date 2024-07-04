package business.feature.impl;

import business.entity.Product;
import business.feature.IProduct;

import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements IProduct {
    public static  List<Product> productList = new ArrayList<Product>();
    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public void addOrUpdate(Product product) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public int findIndexById(Integer id) {
        return 0;
    }
}
