CREATE TABLE IF NOT EXISTS test_table (
    id INT PRIMARY KEY,
    user_name VARCHAR(50),
    email VARCHAR(255)
);

INSERT INTO test_table (id, user_name, email) VALUES
(1, 'John Doe', 'TEST@GMAIL.COM');
