package org.keyin.user;

public class User {
    private int userId = 1; // default, will be overwritten if using DB
    private String userName;
    private String password;
    private String userRole;

    public User(String userName, String password, String userRole) {
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    public User(int userId, String userName, String password, String userRole) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserRole() {
        return userRole;
    }

    public int getUserId() {
        return userId;
    }
}
