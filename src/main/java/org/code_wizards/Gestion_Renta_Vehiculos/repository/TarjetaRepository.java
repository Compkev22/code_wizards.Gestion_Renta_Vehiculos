package org.code_wizards.Gestion_Renta_Vehiculos.repository;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Tarjetas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjetas, Integer> {
}