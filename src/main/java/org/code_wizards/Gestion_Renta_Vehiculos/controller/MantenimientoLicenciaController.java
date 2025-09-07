package org.code_wizards.Gestion_Renta_Vehiculos.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.code_wizards.Gestion_Renta_Vehiculos.entity.Licencias;
import org.code_wizards.Gestion_Renta_Vehiculos.service.ILicenciaService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Data
public class MantenimientoLicenciaController implements Serializable {

    @Inject
    ILicenciaService licenciaService;
    private List<Licencias> licencias;
    private Licencias licenciaSeleccionada;
    private static Logger logger = LoggerFactory.getLogger(MantenimientoLicenciaController.class);

    @PostConstruct
    public void cargarDatos(){
        this.licencias = this.licenciaService.listarLicencias();
        this.licencias.forEach(licencia -> logger.info(licencia.toString()));
    }

    public void agregarLicencia(){
        this.licenciaSeleccionada = new Licencias();
    }

    public void guardarLicencia(){
        logger.info("Licencia a guardar: " + this.licenciaSeleccionada);
        if (this.licenciaSeleccionada.getIdLicencia() == null){
            this.licenciaService.guardarLicencia(this.licenciaSeleccionada);
            this.licencias.add(this.licenciaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Licencia agregada"));
        } else {
            this.licenciaService.guardarLicencia(this.licenciaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Licencia actualizada"));
        }
        PrimeFaces.current().executeScript("PF('ventanaModalLicencia').hide()");
        PrimeFaces.current().ajax().update("formulario-licencias:mensaje-emergente", "formulario-licencias:tabla-licencias");
        this.licenciaSeleccionada = null;
    }

    public void eliminarLicencia(){
        logger.info("Licencia a eliminar: " + this.licenciaSeleccionada);
        this.licenciaService.eliminarLicencia(this.licenciaSeleccionada);
        this.licencias.remove(this.licenciaSeleccionada);
        this.licenciaSeleccionada = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Licencia eliminada"));
        PrimeFaces.current().ajax().update("formulario-licencias:mensaje-emergente", "formulario-licencias:tabla-licencias");
    }
}