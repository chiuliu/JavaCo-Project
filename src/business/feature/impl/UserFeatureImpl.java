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
    static {
        users =  IOFile.readFromFile(IOFile.PATH_USER);
    }

    public UserFeatureImpl() {
        List<Users> user = IOFile.readFromFile(IOFile.PATH_USER);
        if(user == null){
            users = new ArrayList<>();
        }
        users = user;
    }


    @Override
    public void register(Users user) {
        user.setRoleName(RoleName.ROLE_USER);
        users.add(user);
        // cập nhât lại IOFile và chuyền vào users
        IOFile.writeToFile(IOFile.PATH_USER, users);

    }

    @Override
    public Users login(String userName, String password) {
        // tìm ra tk user
        Optional<Users> optionalUsers = users.stream().filter(user -> user.getUserName().equals(userName) && user.getPassword().equals(password)).findFirst();
        return optionalUsers.orElse(null);
        }


    }

