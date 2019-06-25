CREATE DATABASE mercadolivre; 
USE mercadolivre; 

CREATE TABLE dna 
( id bigint unique not null auto_increment, 
  registro longblob not null,
  is_simio tinyint not null,
  primary key (id));
