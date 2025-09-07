package org.code_wizards.Gestion_Renta_Vehiculos.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.code_wizards.Gestion_Renta_Vehiculos.entity.Tarjetas;
import org.code_wizards.Gestion_Renta_Vehiculos.service.ITarjetaService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Data
public class MantenimientoTarjetaController implements Serializable {

    @Inject
    ITarjetaService tarjetaService;
    private List<Tarjetas> tarjetas;
    private Tarjetas tarjetaSeleccionada;
    private static Logger logger = LoggerFactory.getLogger(MantenimientoTarjetaController.class);

    @PostConstruct
    public void cargarDatos(){
        this.tarjetas = this.tarjetaService.listarTarjetas();
        this.tarjetas.forEach(tarjeta -> logger.info(tarjeta.toString()));
    }

    public void agregarTarjeta(){
        this.tarjetaSeleccionada = new Tarjetas();
    }

    public void guardarTarjeta(){
        logger.info("Tarjeta a guardar: " + this.tarjetaSeleccionada);
        if (this.tarjetaSeleccionada.getIdTarjeta() == null){
            this.tarjetaService.guardarTarjeta(this.tarjetaSeleccionada);
            this.tarjetas.add(this.tarjetaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarjeta agregada"));
        } else {
            this.tarjetaService.guardarTarjeta(this.tarjetaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarjeta actualizada"));
        }
        PrimeFaces.current().executeScript("PF('ventanaModalTarjeta').hide()");
        PrimeFaces.current().ajax().update("formulario-tarjetas:mensaje-emergente", "formulario-tarjetas:tabla-tarjetas");
        this.tarjetaSeleccionada = null;
    }

    public void eliminarTarjeta(){
        logger.info("Tarjeta a eliminar: " + this.tarjetaSeleccionada);
        this.tarjetaService.eliminarTarjeta(this.tarjetaSeleccionada);
        this.tarjetas.remove(this.tarjetaSeleccionada);
        this.tarjetaSeleccionada = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarjeta eliminada"));
        PrimeFaces.current().ajax().update("formulario-tarjetas:mensaje-emergente", "formulario-tarjetas:tabla-tarjetas");
    }
}