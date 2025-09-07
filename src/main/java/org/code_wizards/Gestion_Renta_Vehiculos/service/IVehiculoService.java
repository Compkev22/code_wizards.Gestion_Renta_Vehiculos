package org.code_wizards.Gestion_Renta_Vehiculos.service;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Vehiculo;
import java.util.List;

public interface IVehiculoService {
    public List<Vehiculo> listarVehiculos();
    public Vehiculo buscarVehiculoPorId(Integer id);
    public void guardarVehiculo(Vehiculo vehiculo);
    public void eliminarVehiculo(Vehiculo vehiculo);
}