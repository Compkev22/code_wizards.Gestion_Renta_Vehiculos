package org.code_wizards.Gestion_Renta_Vehiculos.repository;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Licencias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenciaRepository extends JpaRepository<Licencias, Integer> {
}