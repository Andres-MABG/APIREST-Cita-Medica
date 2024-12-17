CREATE DATABASE citamedica;

USE citamedica;

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol_id INT NOT NULL,
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);

CREATE TABLE estados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE citas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    medico_id INT,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    especialidad VARCHAR(100) NOT NULL,
    estado_id INT NOT NULL,
    FOREIGN KEY (paciente_id) REFERENCES usuarios(id),
    FOREIGN KEY (medico_id) REFERENCES usuarios(id),
    FOREIGN KEY (estado_id) REFERENCES estados(id)
);

CREATE TABLE pagos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cita_id INT NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    metodo_pago VARCHAR(50),
    fecha_pago TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cita_id) REFERENCES citas(id)
);

CREATE TABLE permisos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL
);

CREATE TABLE rol_permisos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rol_id INT NOT NULL,
    permiso_id INT NOT NULL,
    FOREIGN KEY (rol_id) REFERENCES roles(id),
    FOREIGN KEY (permiso_id) REFERENCES permisos(id)
);

-- Inserción de valores para realizar pruebas de ejemplo
INSERT INTO roles (nombre) VALUES ('paciente'), ('medico');

INSERT INTO estados (nombre) VALUES ('pendiente'), ('pagada'), ('confirmada');

INSERT INTO usuarios (nombre, email, password, rol_id) VALUES ('Pablo Perez', 'pablo@ejemplo.com', '$2b$12$YBqS94HGE1bTXPxwsm/ieuYKolTNlJwdtr9O2kDl/d1ETRG/fA2Ma', 1), ('Dra. Ana Rodriguez', 'ana@ejemplo.com', '$2b$12$149vbUjtHKvN7d37ylT6M.Y6eDXKj1tSc9Bj6O9ZLKtA7hBdtDkw2', 2);
-- Pass de pablo: contraseniahashdepablo
-- Pass de ana: contraseniahashdeana