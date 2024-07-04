package business.feature;

import business.entity.Users;

public interface IUserFeature  {
    // goi ham register
    void register(String fullName, String email, String password);
    // tra ve
    Users login(String email, String password);

}
