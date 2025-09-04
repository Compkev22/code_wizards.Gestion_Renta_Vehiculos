package org.code_wizards.Gestion_Renta_Vehiculos.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Cliente;
import org.code_wizards.Gestion_Renta_Vehiculos.entity.Reserva;
import org.code_wizards.Gestion_Renta_Vehiculos.entity.Vehiculo;
import org.code_wizards.Gestion_Renta_Vehiculos.service.ReservaService;
import org.code_wizards.Gestion_Renta_Vehiculos.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@ViewScoped
public class ReservasController implements Serializable {

    private List<Vehiculo> listaVehiculos;
    private Vehiculo vehiculoSeleccionado;
    private Date fechaInicio;
    private Date fechaFin;

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private ReservaService reservaService;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @PostConstruct
    public void init() {
        listaVehiculos = vehiculoService.obtenerTodos();

        Object vehiculo = FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("vehiculoSeleccionado");
        if (vehiculo instanceof Vehiculo) {
            vehiculoSeleccionado = (Vehiculo) vehiculo;
        }
    }

    public String irAReserva(Vehiculo veh) {
        this.vehiculoSeleccionado = veh;
        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("vehiculoSeleccionado", veh);
        return "reservaVehiculo?faces-redirect=true";
    }

    // Guardar reserva
    public void guardarReserva() {
        try {
            if (vehiculoSeleccionado == null) {
                vehiculoSeleccionado = (Vehiculo) FacesContext.getCurrentInstance()
                        .getExternalContext().getSessionMap().get("vehiculoSeleccionado");
            }

            Cliente clienteLogueado = (Cliente) FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().get("clienteLogueado");

            if (clienteLogueado == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                                "Debes iniciar sesión para reservar"));
                return;
            }

            // Validar fechas
            if (fechaInicio == null || fechaFin == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Fechas incompletas",
                                "Debes seleccionar fecha y hora de inicio y fin"));
                return;
            }

            if (fechaFin.before(fechaInicio)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Fechas incorrectas",
                                "La fecha de fin no puede ser antes de la fecha de inicio"));
                return;
            }

            if (!reservaService.estaDisponible(vehiculoSeleccionado, fechaInicio, fechaFin)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Vehículo no disponible",
                                "El vehículo ya está reservado en esas fechas"));
                return;
            }

            Reserva reserva = new Reserva();
            reserva.setVehiculo(vehiculoSeleccionado);
            reserva.setCliente(clienteLogueado);
            reserva.setFechaInicio(sdf.format(fechaInicio));
            reserva.setFechaFin(sdf.format(fechaFin));

            // Guardar reserva
            reservaService.guardarReserva(reserva);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Reserva Confirmada",
                            "Tu reserva se realizó correctamente"));

            fechaInicio = null;
            fechaFin = null;
            vehiculoSeleccionado = null;

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                            "No se pudo crear la reserva"));
            e.printStackTrace();
        }
    }

    // Getters y Setters
    public List<Vehiculo> getListaVehiculos() { return listaVehiculos; }
    public Vehiculo getVehiculoSeleccionado() { return vehiculoSeleccionado; }
    public void setVehiculoSeleccionado(Vehiculo vehiculoSeleccionado) { this.vehiculoSeleccionado = vehiculoSeleccionado; }
    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }
}
