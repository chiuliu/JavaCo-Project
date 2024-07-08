package presentation.manage;

import business.entity.Address;
import business.feature.IAddress;
import business.feature.impl.AddressImpl;
import business.feature.impl.TryCatchAll;
import presentation.menu.MainUsers;
import run.Main;

import java.util.Scanner;

public class AddressManagement {
    public static IAddress addressFeature = new AddressImpl();

    public static void AddressMenu(Scanner scanner) {
        boolean quit = false;
        do {
            System.out.println(" ============ Trang đia chỉ ============");
            System.out.println(" 1. Thêm địa chỉ mới ");
            System.out.println(" 2. Lấy địa chỉ người dùng theo mã địa chỉ ");
            System.out.println(" 3. Lấy danh sách địa chỉ của người dùng ");
            System.out.println(" 4. Xoá 1 địa chỉ theo mã địa chỉ ");
            System.out.println(" 5. Thoát");
            System.out.println(" =======================================");
            System.out.println(" Lựa chọn của bạn : ");


            int choice = TryCatchAll.inputNumber(scanner);
            switch (choice) {
                case 1:
                    addAddress(scanner);
                    break;
                case 2:
                    showAddressbyId(scanner);
                    break;
                case 3:
                    showAddressList();
                    break;
                case 4:
                    deleteAddress(scanner);
                    break;
                case 5:
                    quit = true;
                    break;
                default:
                    System.err.println("Nhập từ 1- 5");
            }

        }while (!quit);



    }

    private static void deleteAddress(Scanner scanner) {
        System.out.println("Nhập mã địa chỉ cần xoá : ");
        int numberAddress = TryCatchAll.inputNumber(scanner);
        for (Address a : addressFeature.findAll()){
            if (a.getAddressId() == numberAddress && a.getUsers().getId() == MainUsers.currentUser.getId()) {
                addressFeature.findAll().remove(a);
                System.out.println("Xoá thành công");
                break;
            }        }

    }


    private static void showAddressList() {
        if (MainUsers.currentUser == null) {
            System.err.println(" Bạn cần đăng nhập trước đã");
        } else {
            for (Address a : addressFeature.findAll()) {
                if (a.getUsers().getId() == MainUsers.currentUser.getId()) {
                    a.displayAddress();
                }

            }
        }
    }

    private static void showAddressbyId(Scanner scanner) {
        System.out.println("Nhập mã địa chỉ:");
        int number = TryCatchAll.inputNumber(scanner);
        boolean isExist = false;
        for (Address ad : addressFeature.findAll()) {
            if (ad.getAddressId() == number && ad.getUsers().getId() == Main.userLogin.getId()) {
                ad.displayAddress();
                isExist = true;
            }
        }
        if (!isExist) {
            System.err.println("Mã địa chỉ không đúng");
        }
    }

    public static void addAddress(Scanner scanner) {
        System.out.println("Nhập địa chỉ bạn muốn thêm : ");
        Address newAddress = new Address();
        newAddress.inputAddress(scanner, MainUsers.currentUser);
        addressFeature.addOrUpdate(newAddress);
        System.out.println("Đã thêm thành công");


    }

}
