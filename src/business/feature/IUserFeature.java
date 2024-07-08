package business.feature;

import business.entity.Users;

public interface IUserFeature  {
    // goi ham register
    void register(Users users);
    // tra ve
    Users login(String email, String password);

}
