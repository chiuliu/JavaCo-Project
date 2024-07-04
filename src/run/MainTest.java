package run;

import business.constants.RoleName;
import business.entity.Users;
import business.feature.impl.UserFeatureImpl;
import business.utils.IOFile;

public class MainTest {
    public static void main(String[] args) {
		Users users = new Users();
		users.setFullName("ADMIN");
		users.setEmail("admin@gmail.com");
		users.setPassword("admin");
		users.setRoleName(RoleName.ROLE_ADMIN);
		UserFeatureImpl.users.add(users);
		IOFile.writeToFile(IOFile.PATH_USER, UserFeatureImpl.users);
    }
}