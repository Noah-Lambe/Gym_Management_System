# Gym Management System
------------------------------------

## Application Overview

This application is a tool designed to help gyms manage their customers, staff, and offered services. The functionality varies depending on the user's role.

### User Roles:

- **Admin Role**: Admins can:
  - View all users.
  - Delete users.
  - View memberships.
  - See total revenue.

- **Trainer Role**: Trainers can:
  - Manage workout classes (add, update, delete).
  - View their scheduled classes.

- **Member Role**: Members can:
  - Browse workout classes.
  - View their membership expenses.
  - Purchase new memberships.

## Instructions on How to Start and Use the System

### Installation

1. Download or clone the project from GitHub.
2. Ensure that **Java 8** or higher is installed on your system.
3. Set up a database (MySQL or another RDBMS).
   
### Running the Program

1. Navigate to the `GymApp` directory in the terminal.
2. Compile and run the application using the following commands:

    ```bash
    javac -d bin src/org/keyin/GymApp.java
    java org.keyin.GymApp
    ```

### Using the System

After logging in as either an **Admin**, **Trainer**, or **Member**, you will be presented with a specific menu based on your role:

- **Admin**: View, delete users, and see total revenue.
- **Trainer**: Manage workout classes (create, update, delete, view).
- **Member**: Browse classes, view expenses, and purchase memberships.

## Breakdown of Classes and Their Interactions

### User Class (Main Class)

The `User` class serves as the base class for all user types, containing general user information such as:
- Username
- Password
- Email
- Phone number
- Role

### Child Classes:
1. **Admin**: 
   - Inherits from `User` and adds functionality for managing users and viewing revenue.
2. **Trainer**: 
   - Inherits from `User` and adds functionality for managing workout classes.
3. **Member**: 
   - Inherits from `User` and adds functionality for managing memberships.

### Supporting Classes:

- **UserDAO**: 
   - Handles database operations related to users (CRUD operations).
  
- **UserService**: 
   - Provides higher-level functionality for interacting with users, ensuring business logic is followed, and managing the relationship between `User` and `UserDAO`.

---

### Membership Folder:

- **Membership**: 
   - Represents a gym membership with attributes like type, description, and cost.

- **MembershipDAO**: 
   - Handles database CRUD operations related to memberships.

- **MembershipService**: 
   - Provides business logic for managing memberships, including purchasing memberships and viewing expenses.

---

### WorkoutClass Folder:

- **WorkoutClass**: 
   - Represents a workout class, including details like type, description, and trainer ID.

- **WorkoutClassDAO**: 
   - Handles database CRUD operations related to workout classes.

- **WorkoutClassService**: 
   - Provides higher-level services for managing workout classes.

---

### DatabaseConnection Class

The `DatabaseConnection` class is responsible for establishing a connection to the database and initializing the necessary database configurations.

## Setting Up the Database for Development:

### Create Database:

1. Run the `scripts.sql` file to set up the schema for users, workout classes, and memberships.

### Database Connection Configuration:

1. Ensure the `DatabaseConnection` class is properly configured to connect to the database.

---

## Project Structure and Class Diagram

For a detailed class diagram and the project directory structure, please check out the `structure.md` file.

