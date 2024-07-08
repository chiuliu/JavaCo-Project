package run;

import business.constants.RoleName;
import business.entity.Users;
import business.feature.IUserFeature;
import business.feature.impl.UserFeatureImpl;
import presentation.manage.PublicWebsite;
import presentation.menu.MainAdmin;
import presentation.menu.MainModerator;
import presentation.menu.MainUsers;

import java.util.Scanner;

public class Main {
    public static PublicWebsite publicWebsite = new PublicWebsite();
    static IUserFeature userFeature = new UserFeatureImpl();
    public static Users userLogin = null;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("=================== MENU ===================");
            System.out.println("1. Hiển thị sản phẩm");
            System.out.println("2. Đăng nhập");
            System.out.println("3. Đăng ký");
            System.out.println("4. Thoát");
            System.out.println("============================================");
            System.out.print("Lựa chọn của bạn: ");
            int choice = inputNumber(scanner);
            switch (choice) {
                case 1:
                    publicWebsite.PublicWebsiteMenu(scanner);
                    break;
                case 2:
                    handleLogin(scanner);
                    break;
                case 3:
                    handleRegister(scanner);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Vui lòng nhập lại từ 1 -> 4");
            }
        } while (true);
    }

    private static void handleRegister(Scanner scanner) {
        // khai báo đối tượng user
        Users user = new Users();
        // nhập các thông tin
        user.inputUsers(scanner);
        userFeature.register(user);

        System.out.println("Đăng ký thành công.");

    }

    private static void handleLogin(Scanner scanner) {
        System.out.println("Nhập vào tên đăng nhập : ");
        String userName = scanner.nextLine();
        System.out.println("Nhập vào password : ");
        String password = scanner.nextLine();
        Users user =  userFeature.login(userName, password);
        if(user == null) {
            System.err.println("email or password incorrect.");
            return;
        }
        System.out.println("Đăng nhập thành công");
        userLogin = user;


        // check quyền
        if(user.getRoleName().equals(RoleName.ROLE_ADMIN)){
            MainAdmin mainAdmin = new MainAdmin();
            mainAdmin.menu(scanner);
        }
        else if (user.getRoleName().equals(RoleName.ROLE_MODERATOR)){
            MainModerator mainModerator = new MainModerator();
            mainModerator.menu(scanner);

        }
        else {
            MainUsers mainUsers = new MainUsers();
            mainUsers.menu(scanner);

        }

    }

    public static int inputNumber(Scanner scanner) {
        do {
            try {
                return Integer.parseInt(scanner.nextLine());

            }
            catch (NumberFormatException e){
                System.err.println("Vui lòng nhập lại số");

            }

        }
        while (true);
    }
}
