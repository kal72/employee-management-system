-- create database --
CREATE DATABASE attendance_db;
CREATE DATABASE performance_db;

-- employee_db --
CREATE TABLE employee_db.employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    hire_date DATE NOT NULL,
    job_title VARCHAR(100) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    INDEX pk_employee_id(id)
);

-- attendance_db --
CREATE TABLE attendance_db.attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT,
    check_in TIMESTAMP NOT NULL,
    check_out TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    INDEX pk_attendance_id(employee_id),
    INDEX idx_attendance_employee_id(id)
);

-- performance_db --
CREATE TABLE performance_db.performance_review (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT,
    review_date DATE NOT NULL,
    rating INT NOT NULL,
    feedback TEXT,
    INDEX pk_performance_review_id(id),
    INDEX idx_performance_review_employee_id(employee_id)
);

-- dummy data --
USE employee_db;
INSERT INTO employee (first_name, last_name, email, phone_number, hire_date, job_title, salary) VALUES
('John', 'Doe', 'john.doe@example.com', '1234567890', '2023-01-15', 'Software Engineer', 70000.00),
('Jane', 'Smith', 'jane.smith@example.com', '9876543210', '2022-08-20', 'Product Manager', 85000.00),
('Michael', 'Johnson', 'michael.johnson@example.com', '5551234567', '2023-03-10', 'Data Analyst', 60000.00),
('Emily', 'Davis', 'emily.davis@example.com', '4449876543', '2022-11-05', 'Marketing Specialist', 65000.00),
('David', 'Wilson', 'david.wilson@example.com', '3337890123', '2023-02-28', 'HR Coordinator', 55000.00),
('Sarah', 'Martinez', 'sarah.martinez@example.com', '2226547890', '2022-10-15', 'Sales Representative', 60000.00),
('James', 'Brown', 'james.brown@example.com', '1119876543', '2023-04-22', 'Financial Analyst', 70000.00),
('Ashley', 'Taylor', 'ashley.taylor@example.com', '5559876543', '2022-09-18', 'UX/UI Designer', 75000.00),
('Christopher', 'Evans', 'christopher.evans@example.com', '4441234567', '2023-01-30', 'Software Developer', 65000.00),
('Amanda', 'Thomas', 'amanda.thomas@example.com', '3337896541', '2022-12-10', 'Operations Manager', 80000.00);

USE performance_db;
INSERT INTO performance_review (employee_id, review_date, rating, feedback) VALUES
(1, '2023-06-15', 4, 'Great performance overall.'),
(2, '2023-07-20', 5, 'Outstanding job on recent project.'),
(3, '2023-08-10', 3, 'Could improve on meeting deadlines.'),
(4, '2023-09-05', 4, 'Excellent communication skills.'),
(5, '2023-10-28', 3, 'Needs to work on attention to detail.'),
(6, '2023-11-15', 5, 'Consistently exceeds sales targets.'),
(7, '2023-12-22', 4, 'Demonstrates strong analytical skills.'),
(8, '2024-01-18', 5, 'Creative solutions to UX/UI challenges.'),
(9, '2024-02-05', 4, 'Reliable and efficient coding practices.'),
(10, '2024-03-10', 3, 'Could take on more leadership responsibilities.');

