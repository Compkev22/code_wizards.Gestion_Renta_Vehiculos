package org.code_wizards.Gestion_Renta_Vehiculos.service;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Reserva;
import org.code_wizards.Gestion_Renta_Vehiculos.entity.Vehiculo;
import org.code_wizards.Gestion_Renta_Vehiculos.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

    public Reserva guardarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Reserva buscarPorId(Integer id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public void eliminarReserva(Integer id) {
        reservaRepository.deleteById(id);
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