CREATE TABLE IF NOT EXISTS test_data (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    category VARCHAR(255)
);

INSERT INTO test_data (id, name, description, category)
VALUES (1, 'Sample Data 1', '200', '2024-06-02 11:30:00')
ON CONFLICT (id) DO NOTHING;

INSERT INTO test_data (id, name, description, category)
VALUES (2, 'Sample Data 2', '200', '2024-06-02 11:30:00')
ON CONFLICT (id) DO NOTHING;

INSERT INTO test_data (id, name, description, category)
VALUES (3, 'Sample Data 3', '200', '2024-06-02 11:30:00')
ON CONFLICT (id) DO NOTHING;