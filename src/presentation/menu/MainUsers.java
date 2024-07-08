package presentation.menu;

import business.feature.IUserFeature;
import business.feature.impl.TryCatchAll;
import business.feature.impl.UserFeatureImpl;

import java.util.Scanner;

public class MainUsers {
    public static IUserFeature userFeature = new UserFeatureImpl();


    public void menu(Scanner scanner) {
        boolean quit = false;

        do {
            System.out.println("========================= Main User =========================");
            System.out.println("1. Cập nhật thông tin người dùng ");
            System.out.println("2. Thông tin tài khoản người dùng ");
            System.out.println("3. Menu Address ");
            System.out.println("4. Cart Menu ");
            System.out.println("5. Wishlist Menu ");
            System.out.println("6. Đăng xuất  ");
            System.out.println("==============================================================");

            int choice = TryCatchAll.inputNumber(scanner);
            switch (choice) {
                case 1:
                    updateUser(scanner);
                    break;
                case 2:
                    showInformation();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Đăng xuất thành công .");
                    quit = true;
                    break;
                default:
                    System.err.println(" Vui lòng nhập từ 1 - 6.");
            }

        } while (!quit);


    }

    private void showInformation() {
    }

    private void updateUser(Scanner scanner) {
    }
}
