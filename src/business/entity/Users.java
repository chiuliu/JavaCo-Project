package business.entity;

import business.constants.RoleName;
import business.feature.impl.UserFeatureImpl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Users implements Serializable {
    private int id;
    private String userName;
    private String email;
    private String fullName;
    private boolean status;
    private String password;
    private String confirmPassword;
    private String avatar;
    private String phone;
    private String address;
    private Date created;
    private Date updated;
    private byte delete;
    private RoleName roleName;

    public Users() {
    }

    public Users(int id, String userName, String email, String fullName, boolean status, String password, String confirmPassword, String avatar, String phone, String address, Date created, Date updated, byte delete, RoleName roleName) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.status = status;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.avatar = avatar;
        this.phone = phone;
        this.address = address;
        this.created = created;
        this.updated = updated;
        this.delete = delete;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public byte getDelete() {
        return delete;
    }

    public void setDelete(byte delete) {
        this.delete = delete;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public void inputUsers(Scanner scanner){
        this.id = autoIncreaming();
        this.userName = inputUserName(scanner);
        this.email = inputEmail(scanner);
        this.fullName = inputFullName(scanner);
        this.status = true;
        this.password = inputPassWord(scanner);
        this.confirmPassword = inputConfirmPassWord(scanner);
        this.phone = inputPhone(scanner);
        this.address = inputAddress(scanner);
        this.created = new Date();
        this.delete = 1;




    }

    public String inputAddress(Scanner scanner) {
        System.out.println("Nhập địa chỉ:");
        do {
            String adress = scanner.nextLine();
            if (adress.trim().isEmpty()) {
                System.err.println("Không được để trống ");
            } else {
                return adress;
            }
        } while (true);
    }

    private String inputPhone(Scanner scanner) {
        String regex = "(0)\\d{9}";
        System.out.println("Nhập số điện thoại theo dạng VN : ");
        do {
            String phone = scanner.nextLine().trim();
            if (Pattern.matches(regex, phone)) {
                boolean isExit = false;
                for (Users u : UserFeatureImpl.users) {
                    if (u.getPhone().equals(phone)) {
                        isExit = true;
                        break;
                    }
                }
                if (isExit) {
                    System.err.println("Số điện thoại đã tồn tại ");
                } else {
                    return phone;
                }
            } else {
                System.err.println("Số điện thoại phải có 10 ký tự số, bắt đầu từ 0 ");
            }
        } while (true);
        
    }

    private String inputConfirmPassWord(Scanner scanner) {
        System.out.println("Nhập lại mật khẩu:");
        do {
            String confirmPass = scanner.nextLine();

            if (password.equals(confirmPass)) {
                return confirmPass;
            } else {
                System.err.println("Mật khẩu không khớp!");
            }
        } while (true);
    }

    public String inputPassWord(Scanner scanner) {
        System.out.println("Nhập mật khẩu:");
        do {
            String password = scanner.nextLine().trim();
            if (password.isEmpty()) {
                System.err.println("Mật khẩu không được để trống");
            } else if (password.length() < 6) {
                System.err.println("Mật khẩu phải có ít nhất 6 ký tự!");
            } else {
                return password;
            }
        } while (true);

    }

    public String inputFullName(Scanner scanner) {
        System.out.println("Nhập họ và tên : ");
        return scanner.nextLine();
    }

    public String inputEmail(Scanner scanner) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        System.out.println("Mời bạn nhập email:");
        while (true) {
            String email = scanner.nextLine().trim();
            if (Pattern.matches(regex, email)) {
                boolean quit = false;
                for (Users u : UserFeatureImpl.users) {
                    if (u.getEmail().equals(email)) {
                        quit = true;
                        break;
                    }
                }
                if (quit) {
                    System.err.println("Email đã tồn tại,vui lòng nhập lại!");
                } else {
                    return email;
                }
            } else {
                System.err.println("Email không hợp lệ, vui lòng nhập lại!");
            }
        }
    }

    private String inputUserName(Scanner scanner) {
        String regex = "^[a-zA-Z0-9]+$";
        System.out.println("Nhập tên tài khoản : ");
        do {
            String userName = scanner.nextLine();
            if (userName.trim().length() >= 6 && userName.trim().length() <= 100) {
                if (Pattern.matches(regex, userName)) {
                    boolean isExit = false;
                    for (Users u : UserFeatureImpl.users) {
                        if (u.getUserName().equals(userName)) {
                            isExit = true;
                            break;
                        }
                    }
                    if (isExit) {
                        System.err.println("Tài khoản đã tồn tại ");
                    } else {
                        return userName;
                    }
                } else {
                    System.err.println("Không được chứa có ký tự đặc biệt ");
                }
            } else {
                System.err.println("Tài khoản gồm tối thiểu 6 ký tự,nhỏ hơn 100 ký tự,vui lòng nhập lại ");
            }
        } while (true);
    }

    private int autoIncreaming() {
        int max = 0;
        if (!UserFeatureImpl.users.isEmpty()) {
            for (Users u : UserFeatureImpl.users) {
                if (u.getId() > max) {
                    max = u.getId();
                }
            }
        }
        return max + 1;
    }

    public void displayUser(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf(" Tên đăng nhập : %-20s | Email : %-40s | Tên : %-20s |Trạng thái : %-10s \n ",
                this.userName, this. email, this.fullName, this.status? "Hoạt động " : "Khoá");
        System.out.printf("| Số điện thoạt : %-13s| Địa chỉ : %-30s | Date : %-25s \n",
                this.phone, this.address, this.created !=null ? sdf.format(this.created): " ");
    }

}






