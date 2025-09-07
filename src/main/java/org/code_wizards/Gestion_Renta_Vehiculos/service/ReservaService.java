package org.code_wizards.Gestion_Renta_Vehiculos.service;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Reserva;
import org.code_wizards.Gestion_Renta_Vehiculos.entity.Vehiculo;
import org.code_wizards.Gestion_Renta_Vehiculos.repository.ReservaRepository;
import org.code_wizards.Gestion_Renta_Vehiculos.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReservaService implements IReservaService {

    @Autowired
    ReservaRepository reservaRepository;
    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<Reserva> listarReservas() {
        return this.reservaRepository.findAll();
    }

    @Override
    public Reserva buscarReservaPorId(Integer id) {
        return this.reservaRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarReserva(Reserva reserva) {
        this.reservaRepository.save(reserva);
    }

    @Override
    public void eliminarReserva(Reserva reserva) {
        this.reservaRepository.delete(reserva);
    }

    public List<Reserva> obtenerReservasPorVehiculo(int matriculaVehiculo) {
        return reservaRepository.findByVehiculo_Matricula(matriculaVehiculo);
    }

    public boolean estaDisponible(Vehiculo vehiculo, Date fechaInicio, Date fechaFin) {
        List<Reserva> reservas = obtenerReservasPorVehiculo(vehiculo.getMatricula());

        for (Reserva r : reservas) {
            try {
                Date rInicio = formatoFecha.parse(r.getFechaInicio());
                Date rFin = formatoFecha.parse(r.getFechaFin());

                if (!fechaFin.before(rInicio) && !fechaInicio.after(rFin)) {
                    return false; // NO disponible
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return true; // Disponible
    }

}