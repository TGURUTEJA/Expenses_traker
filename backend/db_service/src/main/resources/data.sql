
INSERT INTO userDetails (FirstName, SecondName, age, email) VALUES
('John', 'Doe', '1990-05-15', 'john.doe@example.com'),
('Jane', 'Smith', '1985-08-23', 'jane.smith@example.com'),
('Alice', 'Johnson', '1992-12-01', 'alice.johnson@example.com');

-- Insert test data into userCred
INSERT INTO userCred (id,email, UserName, password, logged_in, user_token, token_expiration) VALUES
(1,'john.doe@example.com', 'johndoe', 'password123', TRUE, 'token123abc', '2025-12-31 23:59:59'),
(2,'jane.smith@example.com', 'janesmith', 'securepass', FALSE, NULL, NULL),
(3,'alice.johnson@example.com', 'alicej', 'alicepwd', TRUE, 'token456def', '2025-11-30 12:00:00');
