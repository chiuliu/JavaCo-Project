package presentation.manage;

import business.entity.Product;
import business.entity.ShoppingCart;
import business.feature.IShoppingCart;
import business.feature.impl.ProductImpl;
import business.feature.impl.ShoppingCartImpl;
import business.feature.impl.TryCatchAll;
import business.utils.IOFile;
import presentation.menu.MainUsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartManagement {
    public static final IShoppingCart cartFeature = new ShoppingCartImpl();

    public static void shoppingCartManagement(Scanner scanner) {
        boolean isExit = false;
        do {
            System.out.println("================Shopping-Cart Management ==============");
            System.out.println("1. Danh sách sản phẩm trong giỏ hàng ");
            System.out.println("2. Thêm mới sản phẩm vào giỏ hàng  ");
            System.out.println("3. Xóa 1 sản phẩm trong giỏ hàng  ");
            System.out.println("4. Xóa toàn bộ sản phẩm trong giỏ hàng ");
            System.out.println("5. Đặt hàng ");
            System.out.println("6. Thoát ");
            System.out.println("======================================================");
            System.out.print(" Lựa chọn của bạn : ");

            int choice = TryCatchAll.inputNumber(scanner);
            switch (choice) {
                case 1:
                    showCart();
                    break;
                case 2:
                    addToCart(scanner);
                    break;
                case 3:
                    deleteProductToCart(scanner);
                    break;
                case 4:
                    deleteAllProductToCart(scanner);
                    break;
                case 5:
                    break;
                case 6:
                    isExit = true;
                    break;
                default:
                    System.err.println(" Nhập từ 1 -6, vui lòng nhập lại ");
            }

        } while (!isExit);


    }

    private static void deleteAllProductToCart(Scanner scanner) {
        List<ShoppingCart> newCarts = new ArrayList<>();
        for (ShoppingCart c : cartFeature.findAll()) {
            if (c.getUsers().getId() != MainUsers.currentUser.getId()) {
                newCarts.add(c);
            }
        }
        System.out.println("Đã xóa toàn bộ sản phẩm trong giỏ hàng");
        IOFile.writeToFile(IOFile.PATH_SHOPPINGCART, ShoppingCartImpl.shoppingCartList);
    }

    private static void deleteProductToCart(Scanner scanner) {
        System.out.println("Nhập mã sp bạn muốn xoá : ");
        int idDelete = TryCatchAll.inputNumber(scanner);
                boolean isExist = false;
        for (ShoppingCart shop : cartFeature.findAll()) {
            if (shop.getShoppingCartId() == idDelete && shop.getUsers().getId() == MainUsers.currentUser.getId()) {
                cartFeature.findAll().remove(shop);
                isExist = true;
                break;
            }
        }
        if (isExist) {
            System.out.println("Xóa sản phẩm thành công");
        } else {
            System.err.println("Mã sản phẩm không có trong giỏ hàng!");
        }
        IOFile.writeToFile(IOFile.PATH_SHOPPINGCART, ShoppingCartImpl.shoppingCartList);
    }

    private static void addToCart(Scanner scanner) {
        for (Product pro : ProductImpl.productList) {
            System.out.printf("| Mã sản phẩm: %-10d| Tên sản phẩm %-20s| Giá:  %-20f| Số lượng: %-10d|\n", pro.getProductId(), pro.getProductName(), pro.getPrice(), pro.getQuantity());
        }

        //tìm mã sản phẩm
        Product adProduct = null;
        System.out.println("Nhập mã sản phẩm:");
        int number = TryCatchAll.inputNumber(scanner);
        boolean isExist = false;
        for (Product pro : ProductImpl.productList) {
            if (pro.getProductId() == number) {
                adProduct = pro;
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            System.err.println("Mã sản phẩm không tồn tại");
        } else {
            ShoppingCart cartItem = null;
            for (ShoppingCart shop : cartFeature.findAll()) {
                if (shop.getProduct().getProductId() == adProduct.getProductId() && shop.getUsers().getId() == MainUsers.currentUser.getId()) {
                    cartItem = shop;
                }
            }
            //Giỏ hàng không có sản phẩm
            if (cartItem == null) {
                cartItem = new ShoppingCart();
                cartItem.inputShoppingCart(scanner, MainUsers.currentUser, adProduct);
                ShoppingCartImpl.shoppingCartList.add(cartItem);
            } else {
                //Giỏ hàng có sản phẩm
                System.out.println("Nhập vào số lượng bạn muốn thêm:");
                int number2 = TryCatchAll.inputNumber(scanner);
                if (cartItem.getOrderQuantity() + number2 <= adProduct.getQuantity()) {
                    cartItem.setOrderQuantity(cartItem.getOrderQuantity() + number2);
                } else {
                    System.err.println("Vượt quá số lượng trong kho!");
                }
            }
        }
        IOFile.writeToFile(IOFile.PATH_SHOPPINGCART, ShoppingCartImpl.shoppingCartList);
    }

    private static void showCart() {
        if (MainUsers.currentUser == null) {
            System.err.println("Vui lòng đăng nhập để xem giỏ hàng");
            return;
        }
        if (cartFeature.findAll().stream().filter(item -> item.getUsers().getId() == MainUsers.currentUser.getId()).count() <= 0) {
            System.err.println("Giỏ hàng trống");
            return;
        }
        for (ShoppingCart shop : cartFeature.findAll()) {
            if (shop.getUsers().getId() == MainUsers.currentUser.getId()) {
                shop.displayShoppingCart();
            }

        }
    }


}
