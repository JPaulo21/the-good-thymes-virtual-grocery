CREATE TABLE customers (
  id SERIAL NOT NULL,
   cpf VARCHAR(11) NOT NULL,
   name VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   enable BOOLEAN NOT NULL,
   url_photo VARCHAR(255),
   customer_since TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   CONSTRAINT pk_customer PRIMARY KEY (id)
);

ALTER TABLE customers ADD CONSTRAINT uc_customers_cpf UNIQUE (cpf);