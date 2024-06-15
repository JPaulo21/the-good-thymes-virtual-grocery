CREATE TABLE customers (
  id SERIAL NOT NULL,
   name VARCHAR(255) NOT NULL,
   customer_since TIMESTAMP with time zone NOT NULL,
   CONSTRAINT pk_customer PRIMARY KEY (id)
);