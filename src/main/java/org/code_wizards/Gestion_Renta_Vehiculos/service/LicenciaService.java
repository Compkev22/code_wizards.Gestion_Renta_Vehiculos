package org.code_wizards.Gestion_Renta_Vehiculos.service;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Licencias;
import org.code_wizards.Gestion_Renta_Vehiculos.repository.LicenciaRepository;
import org.code_wizards.Gestion_Renta_Vehiculos.service.ILicenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenciaService implements ILicenciaService {

    @Autowired
    LicenciaRepository licenciaRepository;

    @Override
    public List<Licencias> listarLicencias() {
        return this.licenciaRepository.findAll();
    }

    @Override
    public Licencias buscarLicenciaPorId(Integer id) {
        return this.licenciaRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarLicencia(Licencias licencia) {
        this.licenciaRepository.save(licencia);
    }

    @Override
    public void eliminarLicencia(Licencias licencia) {
        this.licenciaRepository.delete(licencia);
    }
}