package presentation.manage;

import business.entity.Product;
import business.feature.ICategory;
import business.feature.IProduct;
import business.feature.impl.CategoryImpl;
import business.feature.impl.ProductImpl;
import business.feature.impl.TryCatchAll;

import java.util.Comparator;
import java.util.Scanner;

public class PublicWebsite {
    public static final IProduct productFeature = new ProductImpl();
    public static final ICategory categoryFeature = new CategoryImpl();

    public void PublicWebsiteMenu(Scanner scanner) {
        boolean quit = false;

        do {
            System.out.println("=================== Public Website ===================");
            System.out.println("1. Chi tiết thông tin sản phẩm theo Id ");
            System.out.println("2. Danh sách sản phẩm theo danh mục     ");
            System.out.println("3. Danh sách sản phẩm mới");
            System.out.println("4. Tìm kiếm sản phẩm theo tên hoặc mô tả   ");
            System.out.println("5. Danh sách tất cả sản phẩm được bán    ");
            System.out.println("6. Thoát ");
            System.out.println("=====================================================");
            System.out.print("Lựa chọn của bạn : ");

            int choice = TryCatchAll.inputNumber(scanner);

            switch (choice) {
                case 1:
                    getProductID(scanner);
                    break;
                case 2:
                    showProduct(scanner);
                    break;
                case 3:
                    newProducts();
                    break;
                case 4:
                    searchProduct(scanner);
                    break;
                case 5:
                    listSaleProducts(scanner);
                    break;
                case 6:
                    quit = true;
                    break;
                default:
                    System.err.println("Chọn từ 1 -6 ạ ");
            }

        }
        while (!quit);
    }

    private void listSaleProducts(Scanner scanner) {

        if(productFeature.findAll().isEmpty()){
            System.err.println("Trống sản phẩm");
            return;
        }
        for(Product p : productFeature.findAll()){
            p.displayProduct();
        }
    }

    private void searchProduct(Scanner scanner) {
        System.out.println("Nhập sp cần tìm theo tên sp hoặc mô tả sp:");
        String inputProduct = scanner.nextLine();
        if(inputProduct.trim().isEmpty()){
            System.err.println("Không được để trống");
            return;
        }
        boolean isExist = false;
        for(Product p: productFeature.findAll()) {
            if(p.getProductName().toLowerCase().contains(inputProduct.toLowerCase()) || p.getDescription().toLowerCase().contains(inputProduct.toLowerCase())) {
                p.displayProduct();
                isExist = true;
            }
        }
        if(!isExist) {
            System.err.println("Không tìm thấy sản phẩm " +inputProduct);
        }
    }

    private void newProducts() {
        ProductImpl.productList.sort(Comparator.comparing(Product::getCreated));
        for (Product p : ProductImpl.productList) {
            p.displayProduct();
        }
    }

    private void showProduct(Scanner scanner) {
    }

    private void getProductID(Scanner scanner) {
        System.out.println("Nhập mã sản phẩm:");
        int number = TryCatchAll.inputNumber(scanner);
        boolean isExist = false;
        for (Product p : productFeature.findAll()) {
            if (p.getProductId() == number) {
                p.displayProduct();
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            System.err.println("Nhập sai mã sản phẩm ");
        }
    }
}
