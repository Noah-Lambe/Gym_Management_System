package org.keyin.user.childclasses;

import org.keyin.user.User;

public class Trainer extends User {
  public Trainer(int id, String username, String password, String email, String phoneNumber, String address) {
    super(id, username, password, email, phoneNumber, address, "Trainer");
  }

  public void createWorkoutPlans() {
    System.out.println(getUsername() + " can create workout plans.");
  }
}
