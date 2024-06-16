CREATE TABLE comments (
  id SERIAL NOT NULL,
   text VARCHAR(255) NOT NULL,
   CONSTRAINT pk_comments PRIMARY KEY (id)
);