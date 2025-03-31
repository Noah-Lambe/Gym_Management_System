-- Create user table
CREATE TABLE users(
	user_id SERIAL PRIMARY KEY,
	username TEXT NOT NULL,
	password TEXT NOT NULL,
	email TEXT NOT NULL,
	phone_number VARCHAR(20),
	address TEXT,
	role TEXT check(role IN ('Admin', 'Trainer', 'Member')) NOT NULL
);

-- Create memberships table
CREATE TABLE memberships(
	membership_id SERIAL PRIMARY KEY,
	membership_type TEXT,
	description TEXT,
	cost FLOAT,
	user_id INT REFERENCES users(user_id),
	date_purchased DATE
);

-- Create workout class table
CREATE TABLE workout_classes(
	class_id SERIAL PRIMARY KEY,
	class_type TEXT,
	class_description TEXT,
	trainer_id INT REFERENCES users(user_id)
);