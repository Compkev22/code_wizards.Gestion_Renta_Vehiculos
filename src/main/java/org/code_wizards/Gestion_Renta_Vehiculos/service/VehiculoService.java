package org.code_wizards.Gestion_Renta_Vehiculos.service;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Vehiculo;
import org.code_wizards.Gestion_Renta_Vehiculos.repository.VehiculoRepository;
import org.code_wizards.Gestion_Renta_Vehiculos.service.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService implements IVehiculoService {

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> listarVehiculos() {
        return this.vehiculoRepository.findAll();
    }

    @Override
    public Vehiculo buscarVehiculoPorId(Integer id) {
        return this.vehiculoRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarVehiculo(Vehiculo vehiculo) {
        this.vehiculoRepository.save(vehiculo);
    }

    @Override
    public void eliminarVehiculo(Vehiculo vehiculo) {
        this.vehiculoRepository.delete(vehiculo);
    }
}