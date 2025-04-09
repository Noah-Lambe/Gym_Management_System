# Gym Management System
------------------------------------
## Application Overview

This application is a tool to allow a gym to manage it's customers, staff, and offered services. It offers different functionality
depending on the users role. 

- Admin Role: Admins can view all users, delete users, view memberships, and see total revenue.

- Trainer Role: Trainers can manage their workout classes (add, update, delete) and view their scheduled classes.

- Member Role: Members can browse workout classes, view their membership expenses, and purchase new memberships.

### Instructions on How to Start and Use the System

Installation:

Download the project or clone it from GitHub.

Ensure Java 8 or higher is installed.

Set up a database (MySQL or another RDBMS).

Running the Program:

Navigate to the GymApp directory in the terminal.

Compile and run as follows:

bash
Copy
javac -d bin src/org/keyin/GymApp.java
java org.keyin.GymApp
Using the System:

After logging in as either an Admin, Trainer, or Member, you will be presented with a specific menu depending on your role.

Admin: View, delete users, and view total revenue.

Trainer: Manage workout classes (create, update, delete, view).

Member: Browse classes, view expenses, and purchase memberships.

### Breakdown of Classes and Their Interactions

User Class (Main Class):

The User class is the base class for all user types, holding general user information such as username, password, email, phone number, and role.

Child Classes:

Admin: Inherits from User and includes functionality for managing users and viewing revenue.

Trainer: Inherits from User and includes functionality for managing workout classes.

Member: Inherits from User and includes functionality related to memberships.

UserDAO:

Handles database operations related to users (CRUD operations).

UserService:

Provides higher-level functionality for interacting with users, ensuring business logic is followed and managing the relationship between User and UserDAO.

Membership Folder:

Membership: Represents a gym membership, with attributes like membership type, description, and cost.

MembershipDAO: Handles database operations related to memberships, such as CRUD operations.

MembershipService: Provides business logic for managing memberships, including purchasing memberships and viewing expenses.

WorkoutClass Folder:

WorkoutClass: Represents a workout class, including details like type, description, and trainer ID.

WorkoutClassDAO: Handles database CRUD operations related to workout classes.

WorkoutClassService: Provides higher-level services to manage workout classes.

DatabaseConnection:

Responsible for establishing a connection to the database and initializing the necessary database configurations.



## Project Structure and Class Diagram

For a detailed class diagram and the project directory structure, check out [structure.md](docs/structure.md).
