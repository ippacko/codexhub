CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(200) UNIQUE NOT NULL,
    password_hash VARCHAR(200) NOT NULL,
    role VARCHAR(50) NOT NULL
);
CREATE INDEX idx_users_email ON users(email);
