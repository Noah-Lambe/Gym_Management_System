-- Query to create the database
-- CREATE DATABASE gym_management;

-- Create user table
CREATE TABLE IF NOT EXISTS users(
	user_id SERIAL PRIMARY KEY,
	user_name TEXT NOT NULL,
	user_password TEXT NOT NULL,
	user_email TEXT NOT NULL,
	user_phone VARCHAR(20),
	user_address TEXT,
	user_role TEXT check(user_role IN ('Admin', 'Trainer', 'Member')) NOT NULL
);

-- Create memberships table
CREATE TABLE IF NOT EXISTS memberships(
	membership_id SERIAL PRIMARY KEY,
	membership_type TEXT,
	description TEXT,
	cost FLOAT,
	member_id INT REFERENCES users(user_id)
);

-- Create workout class table
CREATE TABLE IF NOT EXISTS workout_classes(
	class_id SERIAL PRIMARY KEY,
	class_type TEXT,
	class_description TEXT,
	trainer_id INT REFERENCES users(user_id)
);