package business.feature.impl;

import business.constants.RoleName;
import business.entity.Users;
import business.feature.IUserFeature;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserFeatureImpl implements IUserFeature {

    public static List<Users> users = new ArrayList<>();

    public UserFeatureImpl() {
        users = IOFile.readFromFile(IOFile.PATH_USER);
    }


    @Override
    public void register(String fullName, String email, String password) {
        // Khởi tạo đối tượng user
        Users user = new Users();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoleName(RoleName.ROLE_USER);
        // add đối tượng vừa đăng kí vào list
        users.add(user);
        // cập nhật lại list trong file
        IOFile.writeToFile(IOFile.PATH_USER, users);
    }

    @Override
    public Users login(String email, String password) {
        // tìm ra tk user
        Optional<Users> optionalUsers = users.stream().filter(item -> item.getEmail().equals(email) && item.getPassword().equals(password)).findFirst();

        if (optionalUsers.isPresent()) {
            return optionalUsers.get();
        }
            return null;
        }

    }

