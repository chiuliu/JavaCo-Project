package presentation;

import business.entity.Product;
import business.feature.IProduct;
import business.feature.impl.ProductImpl;
import business.feature.impl.TryCatchAll;

import java.util.Date;
import java.util.Scanner;

public class ProductManagement {
    public static IProduct productFeature = new ProductImpl();

    public static void productMenu(Scanner scanner) {
        boolean quit = false;
        do {
            System.out.println("========================= ProductManagement =========================");
            System.out.println("1. Hiển thị danh sách sản phẩm ");
            System.out.println("2. Thêm mới sản phẩm ");
            System.out.println("3. Cập nhật sản phẩm ");
            System.out.println("4. Xóa sản phẩm ");
            System.out.println("5. Tìm sản phẩm theo mã sản phẩm  ");
            System.out.println("6. Thoát  ");
            System.out.println("=====================================================================");
            System.out.println(" Lựa chọn của bạn : ");

            int choice = TryCatchAll.inputNumber(scanner);
            switch (choice) {
                case 1:
                    showAllProduct();
                    break;
                case 2:
                    addProduct(scanner);
                    break;
                case 3:
                    updateProduct(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    findProductById(scanner);
                    break;
                case 6:
                    quit = true;
                    break;
                default:
                    System.err.println(" Bạn cần nhập danh mục từ 1-6");
            }
        } while (!quit);
    }

    private static void findProductById(Scanner scanner) {
        if(productFeature.findAll().isEmpty()){
            System.err.println("Danh mục sản phẩm trống");
            return;
        }
        System.out.println("Nhập mã sản phẩm:");
        int number = TryCatchAll.inputNumber(scanner);
        boolean isExist = false;
        for(Product p:productFeature.findAll()){
            if(p.getProductId() == number){
                p.displayProduct();
                isExist = true;
            }
        }
        if(!isExist){
            System.err.println("Không tìm thấy sản phẩm có mã " +number);
        }
    }

    private static void deleteProduct(Scanner scanner) {
        System.out.println("Nhập mã sản phẩm:");
        int number = TryCatchAll.inputNumber(scanner);
        productFeature.delete(number);
    }

    private static void updateProduct(Scanner scanner) {
        System.out.println("Nhập mã sản phẩm cần update :");
        int idUpdate = TryCatchAll.inputNumber(scanner);
        int indexUpdate = productFeature.findIndexById(idUpdate);
        if (indexUpdate >= 0) {
            Product productUpdate = ProductImpl.productList.get(indexUpdate);
            boolean isExist = true;
            do {
                System.out.println("1. Cập nhật tên sản phẩm");
                System.out.println("2. Cập nhật mô tả sản phẩm ");
                System.out.println("3. Cập nhật giá sản phẩm   ");
                System.out.println("4. Thoát ");
                System.out.println("Lựa chọn của bạn : ");

                int choice = TryCatchAll.inputNumber(scanner);
                switch (choice) {
                    case 1:
                        productUpdate.setProductName(productUpdate.inputProductName(scanner));
                        break;
                    case 2:
                        productUpdate.setDescription(productUpdate.description(scanner));
                        break;
                    case 3:
                        productUpdate.setPrice(productUpdate.inputPrice(scanner));
                        break;
                    case 4:
                        isExist = false;
                        break;
                    default:
                        System.err.println("Lựa chọn từ 1 - 4, vui lòng nhập lại");
                }
                productUpdate.setUpdated(new Date());
                productFeature.addOrUpdate(productUpdate);
            } while (isExist);
        } else {
            System.err.println("Không tim thấy mã sản phẩm : " + indexUpdate);
        }
    }

    private static void addProduct(Scanner scanner) {
        System.out.println("Nhập số lượng sản phẩm:");
        int number = TryCatchAll.inputNumber(scanner);
        for (int i = 0; i < number; i++) {
            Product newProduct = new Product();
            newProduct.inputProduct(scanner);
            productFeature.addOrUpdate(newProduct);
        }

    }

    private static void showAllProduct() {
        if (productFeature.findAll().isEmpty()) {
            System.err.println("Hiện chưa có sản phẩm nào");
        } else {
            for (Product p : productFeature.findAll()) {
                p.displayProduct();
            }
        }

    }
}
