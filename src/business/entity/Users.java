package business.entity;

import business.constants.RoleName;

import java.io.Serializable;

public class Users implements Serializable {
    private int id;
    private String fullName;
    private String email;
    private String password;
    private RoleName roleName;

    // Construction

    public Users() {
    }

    public Users(int id, String fullName, String email, String password, RoleName roleName) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.roleName = roleName;
    }

    // Getter/ Setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}






