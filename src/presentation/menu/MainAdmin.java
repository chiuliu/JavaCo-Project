package presentation.menu;

import business.entity.Users;
import business.feature.impl.TryCatchAll;
import business.feature.impl.UserFeatureImpl;
import presentation.manage.CategoryManagement;
import presentation.manage.ProductManagement;

import java.util.Scanner;

public class MainAdmin {

    public void menu(Scanner scanner) {
        boolean isExit = false;
        do {
            System.out.println("========================= Main Admin =========================");
            System.out.println("1. Quản lý danh mục ");
            System.out.println("2. Quản lý sản phẩm ");
            System.out.println("3. Tìm kiếm người dùng theo tên ");
            System.out.println("4. Lấy ra danh sách quyền ");
            System.out.println("5. Lấy ra danh sách người dùng ");
            System.out.println("6. Đăng xuất  ");
            System.out.println("==============================================================");

            int choice = TryCatchAll.inputNumber(scanner);
            switch (choice) {
                case 1:
                    CategoryManagement.categoryMenu(scanner);
                    break;
                case 2:
                    ProductManagement.productMenu(scanner);
                    break;
                case 3:
                    findUserbyName(scanner);
                    break;
                case 4:
                    showRole();
                    break;
                case 5:
                    showUserList();
                    break;
                case 6:
                    break;
                default:
                    System.err.println(" Vui lòng nhập từ 1 - 6.");
            }

        } while (!isExit);

    }

    private void showRole() {
        System.out.println("======================== Role Admin ==========================");
        System.out.println("======================== Role User ==========================");
        System.out.println("======================== Role Moderator ==========================");
    }

    private void showUserList() {
        if (UserFeatureImpl.users.isEmpty()) {
            System.err.println("Danh sách người dùng trống");
            return;
        }
        for (Users u : UserFeatureImpl.users) {
            u.displayUser();
        }

//        private void showRole() {
//            System.out.println("======================== Role Admin ==========================");
//            System.out.println("======================== Role User ==========================");
//            System.out.println("======================== Role Moderator ==========================");
//        }
    }

    private void findUserbyName(Scanner scanner) {

        if (UserFeatureImpl.users.isEmpty()) {
            System.err.println("Danh mục không tồn tại người dùng");
            return;
        }
        System.out.println("Nhập tên người dùng:");
        String userName = scanner.nextLine();
        boolean found = false;
        for (Users u : UserFeatureImpl.users) {
            if (u.getFullName().toLowerCase().contains(userName.toLowerCase())) {
                u.displayUser();
                found = true;
            }
        }
        if (!found) {
            System.err.println("Không tìm thấy người dùng " + userName);
        }
    }
}
