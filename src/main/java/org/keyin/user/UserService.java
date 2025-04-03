package org.keyin.user;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;

public class UserService {
  private UserDao userDao;

  public UserService() {
    this.userDao = new UserDao();
  }

  // Register a new user (with password hashing)
  public void registerUser(String username, String password, String email, String phoneNumber, String address, String role) throws SQLException {
    String hashedPassword = hashPassword(password);
    User user = new User(0, username, hashedPassword, email, phoneNumber, address, role);
    userDao.registerUser(user);
    System.out.println("User registered successfully!");
  }

  // Authenticate user login (check password)
  public User authenticateUser(String username, String password) throws SQLException {
    User user = userDao.getUserByUsername(username);
    if (user != null && checkPassword(password, user.getPassword())) {
      return user;
    }
    return null;
  }

  // Hash password using BCrypt
  private String hashPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt(12));
  }

  // Check password using BCrypt
  private boolean checkPassword(String password, String hashedPassword) {
    return BCrypt.checkpw(password, hashedPassword);
  }
}

