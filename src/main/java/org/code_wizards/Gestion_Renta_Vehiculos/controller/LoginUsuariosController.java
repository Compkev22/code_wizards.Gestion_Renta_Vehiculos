package org.code_wizards.Gestion_Renta_Vehiculos.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.code_wizards.Gestion_Renta_Vehiculos.entity.Cliente;
import org.code_wizards.Gestion_Renta_Vehiculos.service.IUsuariosLoginService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("loginUsuariosController")
@ViewScoped
public class LoginUsuariosController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(LoginUsuariosController.class.getName());

    @Autowired
    private IUsuariosLoginService clienteService; // ✅ Inyección automática

    // Lista de clientes
    private List<Cliente> clientes;

    // Para seleccionar un cliente
    private Cliente clienteSeleccionado;

    // Para login
    private Cliente clienteLogin;

    // Para registro
    private Cliente nuevoCliente;

    // ================== CONSTRUCTOR ==================
    public LoginUsuariosController() {
        this.clienteLogin = new Cliente();
        this.nuevoCliente = new Cliente();
        this.clienteSeleccionado = new Cliente();
    }

    // ================== POST CONSTRUCT ==================
    @PostConstruct
    public void init() {
        cargarDatos();
    }

    // ================== MÉTODOS ==================
    public void cargarDatos() {
        try {
            this.clientes = clienteService.listarClientes();
            if (this.clientes != null) {
                this.clientes.forEach(cliente -> LOGGER.info(cliente.toString()));
            }
        } catch (Exception e) {
            LOGGER.severe("Error al cargar clientes: " + e.getMessage());
        }
    }

    public String registrarCliente() {
        try {
            clienteService.guardarCliente(this.nuevoCliente);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso",
                            "¡Bienvenido " + this.nuevoCliente.getNombre() + "!"));

            LOGGER.info("Cliente registrado: " + this.nuevoCliente.getCorreo());

            this.nuevoCliente = new Cliente();
            cargarDatos();

            return "loginUsuarios?faces-redirect=true";

        } catch (Exception e) {
            LOGGER.severe("Error al registrar cliente: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar el cliente"));
            return null;
        }
    }

    public String login() {
        try {
            for (Cliente c : clientes) {
                if (c.getCorreo().equals(clienteLogin.getCorreo()) &&
                        c.getContraseña().equals(clienteLogin.getContraseña())) {

                    FacesContext.getCurrentInstance().getExternalContext()
                            .getSessionMap().put("clienteLogueado", c);

                    LOGGER.info("Login exitoso: " + c.getCorreo());
                    return "catalogoVehiculos?faces-redirect=true"; // Redirige a la página principal
                }
            }

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Correo o contraseña incorrectos"));
            return null;

        } catch (Exception e) {
            LOGGER.severe("Error durante login: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error interno al iniciar sesión"));
            return null;
        }
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public Cliente getClienteLogin() {
        return clienteLogin;
    }

    public void setClienteLogin(Cliente clienteLogin) {
        this.clienteLogin = clienteLogin;
    }

    public Cliente getNuevoCliente() {
        return nuevoCliente;
    }

    public void setNuevoCliente(Cliente nuevoCliente) {
        this.nuevoCliente = nuevoCliente;
    }
}
