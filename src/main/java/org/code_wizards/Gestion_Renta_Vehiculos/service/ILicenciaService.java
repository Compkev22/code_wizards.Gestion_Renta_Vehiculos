package org.code_wizards.Gestion_Renta_Vehiculos.service;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Licencias;
import java.util.List;

public interface ILicenciaService {
    public List<Licencias> listarLicencias();
    public Licencias buscarLicenciaPorId(Integer id);
    public void guardarLicencia(Licencias licencia);
    public void eliminarLicencia(Licencias licencia);
}