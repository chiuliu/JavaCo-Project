package business.entity;

import business.feature.impl.AddressImpl;

import java.io.Serializable;
import java.util.Scanner;

public class Address implements Serializable {
    private int addressId;
    private Users users;
    private String fullAddress;
    private String phone;
    private String receiveName;


    public Address() {
    }

    public Address(int addressId, Users users, String fullAddress, String phone, String receiveName) {
        this.addressId = addressId;
        this.users = users;
        this.fullAddress = fullAddress;
        this.phone = phone;
        this.receiveName = receiveName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }


    public void inputAddress(Scanner scanner, Users users){
        this.addressId = autoAddressId();
        this.users = users;
        this.fullAddress = inputFullAddress(scanner);
        this.phone = inputPhoneAddress(scanner);

        this.receiveName = inputReceiveName(scanner);

    }

    private String inputReceiveName(Scanner scanner) {
        System.out.println("Nhập tên người nhận : ");
        return scanner.nextLine();
    }

    private String inputPhoneAddress(Scanner scanner) {
        System.out.println("Nhập số điện thoại :");
        return scanner.nextLine();
    }

    private String inputFullAddress(Scanner scanner) {
        System.out.println("Nhập đủ địa chỉ : ");
        return scanner.nextLine();
    }

    private int autoAddressId() {
        int max = 0;
        for (Address ad : AddressImpl.addressList) {
            if (ad.getAddressId() > max) {
                max = ad.getAddressId();
            }
        }
        return max + 1;
    }

    public void displayAddress(){
        System.out.printf("| Mã địa chỉ: %15d| Tài khoản: %30s| Địa chỉ : %40s|\n",
                this.addressId,this.users.getUserName(),this.fullAddress);
        System.out.printf("| Tên người nhận: %25s| Số điện thoại: %40s|\n",
                this.receiveName,this.phone);


    }
}
