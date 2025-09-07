package org.code_wizards.Gestion_Renta_Vehiculos.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.code_wizards.Gestion_Renta_Vehiculos.entity.Vehiculo;
import org.code_wizards.Gestion_Renta_Vehiculos.service.IVehiculoService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Data
public class MantenimientoVehiculoController implements Serializable {

    @Inject
    IVehiculoService vehiculoService;
    private List<Vehiculo> vehiculos;
    private Vehiculo vehiculoSeleccionado;
    private static Logger logger = LoggerFactory.getLogger(MantenimientoVehiculoController.class);

    @PostConstruct
    public void cargarDatos(){
        this.vehiculos = this.vehiculoService.listarVehiculos();
        this.vehiculos.forEach(vehiculo -> logger.info(vehiculo.toString()));
    }

    public void agregarVehiculo(){
        this.vehiculoSeleccionado = new Vehiculo();
    }

    public void guardarVehiculo(){
        logger.info("Vehículo a guardar: " + this.vehiculoSeleccionado);
        if (this.vehiculoSeleccionado.getMatricula() == null){
            this.vehiculoService.guardarVehiculo(this.vehiculoSeleccionado);
            this.vehiculos.add(this.vehiculoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vehículo agregado"));
        } else {
            this.vehiculoService.guardarVehiculo(this.vehiculoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vehículo actualizado"));
        }
        PrimeFaces.current().executeScript("PF('ventanaModalVehiculo').hide()");
        PrimeFaces.current().ajax().update("formulario-vehiculos:mensaje-emergente", "formulario-vehiculos:tabla-vehiculos");
        this.vehiculoSeleccionado = null;
    }

    public void eliminarVehiculo(){
        logger.info("Vehículo a eliminar: " + this.vehiculoSeleccionado);
        this.vehiculoService.eliminarVehiculo(this.vehiculoSeleccionado);
        this.vehiculos.remove(this.vehiculoSeleccionado);
        this.vehiculoSeleccionado = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vehículo eliminado"));
        PrimeFaces.current().ajax().update("formulario-vehiculos:mensaje-emergente", "formulario-vehiculos:tabla-vehiculos");
    }
}