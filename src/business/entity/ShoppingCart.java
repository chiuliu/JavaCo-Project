package business.entity;

import business.feature.impl.ShoppingCartImpl;
import business.feature.impl.TryCatchAll;

import java.io.Serializable;
import java.util.Scanner;

public class ShoppingCart implements Serializable {
    private int shoppingCartId;
    private Product product;
    private Users users;
    private int orderQuantity;

    public ShoppingCart() {
    }

    public ShoppingCart(int shoppingCartId, Product product, Users users, int orderQuantity) {
        this.shoppingCartId = shoppingCartId;
        this.product = product;
        this.users = users;
        this.orderQuantity = orderQuantity;
    }

    public int getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public void inputShoppingCart(Scanner scanner, Users users, Product product){
        this.shoppingCartId = autoShoppingCartId();
        this.product = product;
        this.users = users;
        this.orderQuantity = inputOrderQuantity(scanner, product);

    }

    private int inputOrderQuantity(Scanner scanner, Product product) {
        System.out.println("Mời bạn nhập số lượng:");
        do {
            int number = TryCatchAll.inputNumber(scanner);
            if (number <= 0) {
                System.err.println("Số lượng phải > 0,vui lòng nhâp lại");
            } else {
                if (number <= product.getQuantity()) {
                    return number;
                } else {
                    System.err.println("Số lượng sản phẩm trong kho không đủ, vui lòng nhập lại");
                }
            }
        } while (true);
    }


    public void displayShoppingCart(){
        System.out.printf("|Mã giỏ hàng: %-10d| Sản phẩm: %-20s| Số lượng: %-10d|\n",
                this.shoppingCartId, this.product.getProductName(), this.orderQuantity);

    }

    private int autoShoppingCartId() {
        int max = 0;
        for (ShoppingCart shop : ShoppingCartImpl.shoppingCartList) {
            if (shop.shoppingCartId > max) {
                max = shop.shoppingCartId;
            }
        }
        return max + 1;
    }
}