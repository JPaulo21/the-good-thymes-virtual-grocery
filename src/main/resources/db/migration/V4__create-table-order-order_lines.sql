CREATE TABLE orders (
  id SERIAL NOT NULL,
   date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   customer_id INTEGER NOT NULL,
   CONSTRAINT pk_orders PRIMARY KEY (id)
);

ALTER TABLE orders ADD CONSTRAINT FK_ORDERS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);

CREATE TABLE order_lines (
  order_id INTEGER NOT NULL,
   product_id INTEGER NOT NULL,
   amount INTEGER NOT NULL,
   purchase_price DECIMAL NOT NULL
);

ALTER TABLE order_lines ADD CONSTRAINT fk_order_lines_on_order FOREIGN KEY (order_id) REFERENCES orders (id);