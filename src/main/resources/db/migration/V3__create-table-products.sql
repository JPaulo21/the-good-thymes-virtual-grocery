CREATE TABLE products (
  id SERIAL NOT NULL,
   name VARCHAR(255) NOT NULL,
   price DECIMAL NOT NULL,
   in_stock BOOLEAN NOT NULL,
   CONSTRAINT pk_products PRIMARY KEY (id)
);