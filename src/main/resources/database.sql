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
     rol enum('cliente','administrador') default 'cliente',
     direccion varchar(128),
     nit varchar(15) default 'CF',
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


