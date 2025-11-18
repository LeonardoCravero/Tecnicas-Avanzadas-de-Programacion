CREATE DATABASE IF NOT EXISTS tienda;
USE tienda;

CREATE TABLE IF NOT EXISTS producto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  descripcion TEXT,
  precio_compra DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  precio_venta DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  cantidad INT NOT NULL DEFAULT 0
);

-- Insertar 10 registros iniciales
INSERT INTO producto (nombre, descripcion, precio_compra, precio_venta, cantidad) VALUES
('Taladro eléctrico', 'Taladro 500W con percutor', 12000.00, 13500.00, 10),
('Martillo', 'Martillo de acero 16oz', 1500.00, 1750.00, 25),
('Destornillador set', 'Set 6 piezas imantadas', 2200.00, 2500.00, 30),
('Sierra manual', 'Sierra manual 22 pulgadas', 3500.00, 3700.00, 8),
('Alicate', 'Alicate universal 8"', 1800.00, 2500.00, 20),
('Cinta métrica', '5m x 19mm', 700.00, 1100.00, 50),
('Lima metal', 'Lima para metal fina', 450.00, 600.00, 40),
('Tornillo 4x20', 'Bolsa 100 unidades', 800.00, 950.00, 100),
('Tuerca M8', 'Bolsa 50 unidades', 600.00, 700.00, 200),
('Pintura acrílica 1L', 'Blanco mate', 4200.00, 4600.00, 15);