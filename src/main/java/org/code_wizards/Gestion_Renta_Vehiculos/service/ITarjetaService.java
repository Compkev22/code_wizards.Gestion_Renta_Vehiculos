package org.code_wizards.Gestion_Renta_Vehiculos.service;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Tarjetas;
import java.util.List;

public interface ITarjetaService {
    public List<Tarjetas> listarTarjetas();
    public Tarjetas buscarTarjetaPorId(Integer id);
    public void guardarTarjeta(Tarjetas tarjeta);
    public void eliminarTarjeta(Tarjetas tarjeta);
}