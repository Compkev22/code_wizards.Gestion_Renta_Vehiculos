package org.code_wizards.Gestion_Renta_Vehiculos.service;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Tarjetas;
import org.code_wizards.Gestion_Renta_Vehiculos.repository.TarjetaRepository;
import org.code_wizards.Gestion_Renta_Vehiculos.service.ITarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarjetaService implements ITarjetaService {

    @Autowired
    TarjetaRepository tarjetaRepository;

    @Override
    public List<Tarjetas> listarTarjetas() {
        return this.tarjetaRepository.findAll();
    }

    @Override
    public Tarjetas buscarTarjetaPorId(Integer id) {
        return this.tarjetaRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarTarjeta(Tarjetas tarjeta) {
        this.tarjetaRepository.save(tarjeta);
    }

    @Override
    public void eliminarTarjeta(Tarjetas tarjeta) {
        this.tarjetaRepository.delete(tarjeta);
    }
}