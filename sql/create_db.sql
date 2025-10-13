-- create_db.sql (versão limpa, sem index duplicado)
CREATE DATABASE IF NOT EXISTS achadinhosdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE achadinhosdb;

CREATE TABLE IF NOT EXISTS product (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  size VARCHAR(50),
  description TEXT,
  city VARCHAR(100),
  payment_method VARCHAR(255),
  seller_name VARCHAR(150),
  seller_phone VARCHAR(50),
  seller_email VARCHAR(150),
  photo1 VARCHAR(255),
  photo2 VARCHAR(255),
  photo3 VARCHAR(255),
  photo4 VARCHAR(255),
  photo5 VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Sample data (um produto de exemplo para testar)
INSERT INTO product (title, price, size, description, city, payment_method, seller_name, seller_phone, seller_email, photo1)
VALUES ('Camisa Polo', 59.99, 'M', 'Camisa básica ideal para o dia a dia.', 'Gama - DF', 'Direto com o anunciante', 'Exemplo', '+55 61 9999-9999', 'vendedor@achadinhosdavez.com', NULL);