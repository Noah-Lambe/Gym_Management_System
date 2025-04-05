package org.keyin.user;

//*
// This is the parent class for all users, There are 3 types of users: Trainer, Member, and Admin
//
// *//
public class User {
  private int id;
  private String username;
  private String password;
  private String email;
  private String phoneNumber;
  private String address;
  private String role;

  // Constructor
  public User(String username, String password, String email, String phoneNumber, String address, String role) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.role = role;
  }

  // Getters
  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public String getRole() {
    return role;
  }

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setRole(String role) {
    this.role = role;
  }

  // Method to display user info
  @Override
  public String toString() {
    return "User{'username=" + username + '\'' + ", email='" + email + '\'' + ", phoneNumber='"
        + phoneNumber + '\'' + ", address='" + address + '\'' + ", role='" + role + '\'' + '}';
  }
}
