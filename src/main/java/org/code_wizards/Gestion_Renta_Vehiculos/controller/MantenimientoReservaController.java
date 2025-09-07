package org.code_wizards.Gestion_Renta_Vehiculos.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.code_wizards.Gestion_Renta_Vehiculos.entity.Reserva;
import org.code_wizards.Gestion_Renta_Vehiculos.service.IReservaService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Data
public class MantenimientoReservaController implements Serializable {

    @Inject
    IReservaService reservaService;
    private List<Reserva> reservas;
    private Reserva reservaSeleccionada;
    private static Logger logger = LoggerFactory.getLogger(MantenimientoReservaController.class);

    @PostConstruct
    public void cargarDatos(){
        this.reservas = this.reservaService.listarReservas();
        this.reservas.forEach(reserva -> logger.info(reserva.toString()));
    }

    public void agregarReserva(){
        this.reservaSeleccionada = new Reserva();
    }

    public void guardarReserva(){
        logger.info("Reserva a guardar: " + this.reservaSeleccionada);
        if (this.reservaSeleccionada.getIdReserva() == null){
            this.reservaService.guardarReserva(this.reservaSeleccionada);
            this.reservas.add(this.reservaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Reserva agregada"));
        } else {
            this.reservaService.guardarReserva(this.reservaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Reserva actualizada"));
        }
        PrimeFaces.current().executeScript("PF('ventanaModalReserva').hide()");
        PrimeFaces.current().ajax().update("formulario-reservas:mensaje-emergente", "formulario-reservas:tabla-reservas");
        this.reservaSeleccionada = null;
    }

    public void eliminarReserva(){
        logger.info("Reserva a eliminar: " + this.reservaSeleccionada);
        this.reservaService.eliminarReserva(this.reservaSeleccionada);
        this.reservas.remove(this.reservaSeleccionada);
        this.reservaSeleccionada = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Reserva eliminada"));
        PrimeFaces.current().ajax().update("formulario-reservas:mensaje-emergente", "formulario-reservas:tabla-reservas");
    }
}