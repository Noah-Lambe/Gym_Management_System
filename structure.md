## Class Diagram and Relationship Between Entities

``` plaintext
                        +-----------------+
                        |      User       |
                        +-----------------+
                        | - username      |
                        | - password      |
                        | - email         |
                        | - phoneNumber   |
                        | - address       |
                        | - role          |
                        +-----------------+
                                ^
        -------------------------|-------------------------
       |                         |                         |
 +--------------+        +---------------+        +---------------+
 |    Admin     |        |    Trainer    |        |    Member     |
 +--------------+        +---------------+        +---------------+
                             ^
                             |
                         +-------------------+
                         |  WorkoutClass     |
                         +-------------------+
                         | - workoutType     |
                         | - description     |
                         | - trainerId       |
                         +-------------------+
                             ^
                             |
                        +-------------------+
                        | WorkoutClassDAO   |
                        +-------------------+
                        | - add()           |
                        | - update()        |
                        | - delete()        |
                        +-------------------+
                             ^
                             |
                       +---------------------+
                       | WorkoutClassService |
                       +---------------------+
                       | - addWorkoutClass() |
                       | - updateClass()     |
                       | - deleteClass()     |
                       +---------------------+

                             ^
                             |
                         +-------------------+
                         |    Membership     |
                         +-------------------+
                         | - membershipType  |
                         | - description     |
                         | - cost            |
                         | - memberId        |
                         +-------------------+
                             ^
                             |
                        +-------------------+
                        | MembershipDAO     |
                        +-------------------+
                        | - add()           |
                        | - update()        |
                        | - delete()        |
                        +-------------------+
                             ^
                             |
                       +---------------------+
                       | MembershipService   |
                       +---------------------+
                       | - purchaseMembership()|
                       | - viewExpenses()     |
                       +---------------------+
```

## Project Directory Structure

``` plaintext
/src
    /main
        /java
            /org/keyin
                GymApp.java         <-- Main entry point
                /user
                    User.java        <-- Base user class
                    /childClasses
                        Admin.java   <-- Admin role class
                        Trainer.java <-- Trainer role class
                        Member.java  <-- Member role class
                    UserDAO.java     <-- User data access object
                    UserService.java <-- User-related service logic
                /membership
                    Membership.java  <-- Represents a membership
                    MembershipDAO.java <-- Handles DB operations for memberships
                    MembershipService.java <-- Service layer for managing memberships
                /workoutClass
                    WorkoutClass.java        <-- Represents a workout class
                    WorkoutClassDAO.java     <-- Handles DB operations for classes
                    WorkoutClassService.java <-- Service layer for workout classes
                /database
                    DatabaseConnection.java  <-- Initializes DB connection
```

## Build Process and Dependencies Used:

### Build Process:

- Use javac for manual compilation or use Maven/Gradle if preferred.

### Dependencies:

- JDBC for database interaction.

- PostgreSQL.