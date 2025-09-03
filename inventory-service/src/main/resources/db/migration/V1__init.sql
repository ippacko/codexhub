CREATE TABLE copies (
  id UUID PRIMARY KEY,
  book_id UUID NOT NULL,
  status VARCHAR(20) NOT NULL
);
CREATE TABLE reservations (
  id UUID PRIMARY KEY,
  copy_id UUID NOT NULL,
  user_email VARCHAR(200) NOT NULL,
  reserved_at TIMESTAMP NOT NULL
);
CREATE INDEX idx_reservations_user ON reservations(user_email);
