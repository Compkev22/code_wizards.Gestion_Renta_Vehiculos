package org.code_wizards.Gestion_Renta_Vehiculos.repository;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    Vehiculo findByModelo(String modelo);
}