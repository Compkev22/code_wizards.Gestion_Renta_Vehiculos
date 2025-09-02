// Archivo: src/main/java/org/code_wizards/Gestion_Renta_Vehiculos/repository/ClienteRepository.java

package org.code_wizards.Gestion_Renta_Vehiculos.repository;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByCorreoAndContraseña(String correo, String contraseña);
}