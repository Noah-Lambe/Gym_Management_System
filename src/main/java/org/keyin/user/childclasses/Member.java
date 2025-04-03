package org.keyin.user.childclasses;

import org.keyin.user.User;

public class Member extends User {
  public Member(int id, String username, String password, String email, String phoneNumber, String address) {
    super(id, username, password, email, phoneNumber, address, "Member");
  }

  public void viewWorkouts() {
    System.out.println(getUsername() + " can view assigned workouts.");
  }
}

