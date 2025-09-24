DROP TABLE IF EXISTS userCred CASCADE;
DROP TABLE IF EXISTS userDetails CASCADE;

CREATE TABLE IF NOT EXISTS userDetails (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    birth_date DATE
);

CREATE TABLE IF NOT EXISTS userCred (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES userDetails(id)
        ON DELETE CASCADE
);
