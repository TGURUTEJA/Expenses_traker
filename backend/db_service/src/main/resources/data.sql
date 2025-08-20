
INSERT INTO userDetails (FirstName, SecondName, age, email) VALUES
('John', 'Doe', '1990-05-15', 'john.doe@example.com'),
('Jane', 'Smith', '1985-08-23', 'jane.smith@example.com'),
('Alice', 'Johnson', '1992-12-01', 'alice.johnson@example.com'),
('test', 'user', '1995-01-01', 'test@test.com');

-- Insert test data into userCred
INSERT INTO userCred (email, UserName, password) VALUES
('john.doe@example.com', 'johndoe', 'password123'),
('jane.smith@example.com', 'janesmith', 'securepass'),
('alice.johnson@example.com', 'alicej', 'alicepwd'),
('test@test.com', 'testuser', 'testpass');

SELECT setval('usercred_id_seq',
COALESCE((SELECT MAX(id) FROM usercred), 0) + 1,
false);
SELECT setval('userdetails_id_seq',
COALESCE((SELECT MAX(id) FROM userdetails), 0) + 1,
false);