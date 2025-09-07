package org.code_wizards.Gestion_Renta_Vehiculos.service;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Reserva;
import java.util.List;

public interface IReservaService {
    public List<Reserva> listarReservas();
    public Reserva buscarReservaPorId(Integer id);
    public void guardarReserva(Reserva reserva);
    public void eliminarReserva(Reserva reserva);
}