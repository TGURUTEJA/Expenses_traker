-- Insert into userDetails
INSERT INTO userDetails (first_name, last_name, birth_date) VALUES
('John',  'Doe',     '1990-05-15'),
('Jane',  'Smith',   '1985-08-23'),
('Alice', 'Johnson', '1992-12-01'),
('Test',  'User',    '1995-01-01');

-- Insert into userCred (linked via user_id)
INSERT INTO userCred (username, email, password, user_id) VALUES
('johndoe',   'john.doe@example.com',   'password123', 1),
('janesmith', 'jane.smith@example.com', 'securepass',  2),
('alicej',    'alice.johnson@example.com', 'alicepwd', 3),
('testuser',  'test@test.com',          'testpass',    4);

-- Reset sequences
SELECT setval('usercred_id_seq',
    COALESCE((SELECT MAX(id) FROM usercred), 0) + 1,
    false);

SELECT setval('userdetails_id_seq',
    COALESCE((SELECT MAX(id) FROM userdetails), 0) + 1,
    false);
