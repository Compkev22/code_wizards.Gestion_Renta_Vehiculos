package org.code_wizards.Gestion_Renta_Vehiculos.repository;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginUsuariosRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByCorreoAndContraseña(String correo, String contrasena);
}