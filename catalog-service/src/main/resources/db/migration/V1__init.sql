CREATE TABLE authors (
  id UUID PRIMARY KEY,
  name VARCHAR(200) NOT NULL
);
CREATE TABLE books (
  id UUID PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  author_id UUID NOT NULL REFERENCES authors(id),
  published_year INT
);
CREATE INDEX idx_books_title ON books(title);
