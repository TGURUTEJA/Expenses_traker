DROP TABLE IF EXISTS userCred CASCADE;
DROP TABLE IF EXISTS userDetails CASCADE;

CREATE TABLE IF NOT EXISTS userDetails (
    id BIGSERIAL PRIMARY KEY,
    FirstName VARCHAR(255),
    SecondName VARCHAR(255),
    age DATE,
    email VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS userCred (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    UserName VARCHAR(255),
    password VARCHAR(255),
    CONSTRAINT fk_user_email
        FOREIGN KEY (email)
        REFERENCES userDetails(email)
        ON DELETE CASCADE
);
