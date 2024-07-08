package business.entity;

import business.feature.impl.WishListImpl;

import java.util.Scanner;

public class WishList {
    private int wishListId;
    private Users id;
    private Product productId;

    public WishList() {
    }

    public WishList(Users id, Product productId, int wishListId) {
        this.id = id;
        this.productId = productId;
        this.wishListId = wishListId;
    }

    public Users getId() {
        return id;
    }

    public void setId(Users id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }
    //Nhập dữ liệu danh sách yêu thích
    public void inputWishList(Scanner sc, Product productId, Users userId) {
        this.wishListId = autoWishListId();
        this.productId = productId;
        this.id = userId;
    }
    // ID tự tăng
    private int autoWishListId() {
        int max = 0;
        for (WishList w : WishListImpl.wishLists) {
            if (w.getWishListId() > max) {
                max = w.getWishListId();
            }
        }
        return max + 1;
    }
    //Hiển thị danh sách yêu thích
    public void displayWishList() {
        System.out.printf("|ID: %-10d| Tên sản phẩm:%-30s| \n",this.wishListId,this.productId.getProductName());
    }
}
