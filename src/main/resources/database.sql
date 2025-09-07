drop database if exists renta_vehiculos_code_wizards;
create database renta_vehiculos_code_wizards;
use renta_vehiculos_code_wizards;

create table Vehiculos(
	matricula int auto_increment,
    marca varchar(64),
    modelo varchar(64),
    año varchar(12),
    tipo varchar(64),
    precioDiario int,
    disponibilidad enum ('disponible', 'no disponible') default 'disponible',
	constraint pk_vehiculos primary key(matricula)
);

create table Clientes(
	idCliente int auto_increment,
    nombre varchar(64),
    apellido varchar(64),
    telefono varchar(64),
    correo varchar(64),
    contraseña varchar(64),
    direccion varchar(128),
    nit varchar(64),
    rol enum ('cliente','administrador') default 'cliente',
    constraint pk_clientes primary key(idCliente)
);

create table Tarjetas(
	idTarjeta int auto_increment,
    PAN varchar(64),
    CVV varchar(64),
    fechaVencimiento varchar(64),
    idCliente int, 
    constraint pk_tarjetas primary key(idTarjeta),
    constraint fk_tarjetas_cliente foreign key (idCliente) references Clientes(idCliente)
);

create table Licencias(
	idLicencia int auto_increment,
    tipo varchar(64),
    nombre varchar(64),
    apellido varchar(64),
    fechaVencimiento varchar(64),
    idCliente int,
    constraint pk_licencias primary key(idLicencia),
    constraint fk_licencias_cliente foreign key (idCliente) references Clientes(idCliente)
);

create table Reservas(
	idReserva int auto_increment,
    fechaInicio varchar(64),
    fechaFin varchar(64),
    costoTotal varchar(64),
    matricula int,
    idCliente int,
    constraint pk_reservas primary key(idReserva),
    constraint fk_reservas_vehiculo foreign key (matricula) references Vehiculos(matricula),
    constraint fk_reservas_cliente foreign key (idCliente) references Clientes(idCliente)
);

INSERT INTO Vehiculos (marca, modelo, año, tipo, precioDiario, disponibilidad) VALUES
('Toyota', 'Corolla', '2023', 'Sedán', 50, 'disponible'),
('Honda', 'CRV', '2022', 'SUV', 75, 'disponible'),
('Ford', 'Mustang', '2021', 'Deportivo', 120, 'disponible'),
('Nissan', 'Sentra', '2020', 'Sedán', 45, 'disponible'),
('Hyundai', 'Elantra', '2023', 'Sedán', 55, 'disponible');

INSERT INTO Clientes(nombre, apellido, telefono, correo, contraseña, direccion, nit, rol)
VALUES 	('Kenneth','Caceres','1234-4567','kadmin.com','admin123','direccion 123','123123','administrador'),
		('Marcos','Cho', '2345-7891','mcli.com','cliente123','direccion 123','345345','cliente'),
        ('Roberto', 'Gomez', '3456-7890', 'roberto.gomez@mail.com', 'roberto456', 'Calle Principal #123', '987987', 'cliente'),
		('Laura', 'Diaz', '4567-8901', 'laura.diaz@mail.com', 'laura789', 'Avenida del Sol #45', '654654', 'cliente'),
		('Pedro', 'Morales', '5678-9012', 'pedro.morales@mail.com', 'pedro123', 'Paseo de las Flores #67', '321321', 'cliente');

INSERT INTO Tarjetas (PAN, CVV, fechaVencimiento, idCliente) VALUES
('1234-5678-9012-3456', '123', '12/25', 1),
('9876-5432-1098-7654', '456', '10/24', 2),
('1111-2222-3333-4444', '789', '05/26', 3),
('5555-6666-7777-8888', '111', '08/27', 4),
('9999-0000-1111-2222', '222', '03/24', 5);

INSERT INTO Licencias (tipo, nombre, apellido, fechaVencimiento, idCliente) VALUES
('A', 'Kenneth', 'Caceres', '11/27', 1),
('B', 'Marcos', 'Cho', '03/25', 2),
('C', 'Roberto', 'Gomez', '07/26', 3),
('B', 'Laura', 'Diaz', '12/28', 4),
('A', 'Pedro', 'Morales', '04/25', 5);

INSERT INTO Reservas (fechaInicio, fechaFin, costoTotal, matricula, idCliente) VALUES
('2024-05-10', '2024-05-12', '150', 1, 1),
('2024-05-15', '2024-05-18', '225', 2, 2),
('2024-06-01', '2024-06-05', '600', 3, 3),
('2024-06-10', '2024-06-12', '150', 4, 4),
('2024-07-01', '2024-07-03', '165', 5, 5);


