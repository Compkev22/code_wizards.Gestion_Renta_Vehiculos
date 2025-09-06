package org.code_wizards.Gestion_Renta_Vehiculos.controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import org.code_wizards.Gestion_Renta_Vehiculos.entity.Cliente;
import org.code_wizards.Gestion_Renta_Vehiculos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

@Named("loginAdministradorController")
@RequestScoped
public class LoginAdministradorController {

    private String correo;
    private String contraseña;

    @Autowired
    private ClienteService clienteService;

    public String iniciarSesion() {
        Cliente cliente = clienteService.autenticar(correo, contraseña);

        if (cliente != null) {
            if ("administrador".equals(cliente.getRol())) {
                return "administracion.xhtml?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acceso Denegado", "El usuario no es un administrador."));
                return null;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Inicio de Sesión", "Correo o contraseña incorrectos."));
            return null;
        }
    }

    // Getters y Setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}