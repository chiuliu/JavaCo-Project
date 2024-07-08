package presentation.menu;

import business.entity.Users;
import business.feature.IUserFeature;
import business.feature.impl.TryCatchAll;
import business.feature.impl.UserFeatureImpl;
import presentation.manage.AddressManagement;
import run.Main;

import java.util.Scanner;

public class MainUsers {
    public static IUserFeature userFeature = new UserFeatureImpl();
    // gọi đến acc đăng nhập hiện tại
    public static Users currentUser = Main.userLogin;


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
            System.out.println(" Lựa chọn của bạn : ");

            int choice = TryCatchAll.inputNumber(scanner);
            switch (choice) {
                case 1:
                    updateUser(scanner);
                    break;
                case 2:
                    showInformation();
                    break;
                case 3:
                    AddressManagement.AddressMenu(scanner);
                    break;
                case 4:
                    shoppingCart();
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

    private void shoppingCart() {
    }

    private void showInformation() {
        currentUser.displayUser();
    }

    private void updateUser(Scanner scanner) {
        boolean isExit = false;
        do {

            System.out.println(" Chọn mục bạn muốn Update : ");
            System.out.println("1. Họ và tên mới ");
            System.out.println("2. Email mới ");
            System.out.println("3. Mật khẩu mới ");
            System.out.println("4. Địa chỉ mới ");
            System.out.println("5. Thoát. ");
            System.out.println("==============================");
            System.out.print("Lựa chọn của bạn là :  ");

            int choice = TryCatchAll.inputNumber(scanner);

            switch (choice) {
                case 1:
                    currentUser.setFullName(currentUser.inputFullName(scanner));
                    break;
                case 2:
                    currentUser.setEmail(currentUser.inputEmail(scanner));
                    break;
                case 3:
                    currentUser.setPassword(currentUser.inputPassWord(scanner));
                    break;
                case 4:
                    currentUser.setAddress(currentUser.inputAddress(scanner));
                    break;
                case 5:
                    isExit = true;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1 -5");
            }

        } while (!isExit);

    }
}
