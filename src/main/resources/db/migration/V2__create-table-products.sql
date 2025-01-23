CREATE TABLE products (
  id SERIAL NOT NULL,
   name VARCHAR(255) NOT NULL,
   describe VARCHAR(4000),
   price DECIMAL(11,2) NOT NULL,
   in_stock BOOLEAN NOT NULL,
   url_imagem varchar2 NULL,
   CONSTRAINT pk_products PRIMARY KEY (id)
);