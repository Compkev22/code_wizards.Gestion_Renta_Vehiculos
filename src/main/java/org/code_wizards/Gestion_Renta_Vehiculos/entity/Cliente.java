package org.code_wizards.Gestion_Renta_Vehiculos.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Clientes")
@Data//generar los setters y getters
@NoArgsConstructor//el constructor vacio
@AllArgsConstructor//el constructor lleno
@EqualsAndHashCode//el metodo para trabajar con HashCode// id interno para la clase
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String telefono ;
    private String correo ;
    private String contraseña ;
    private String direccion;
    private String nit;
    private String rol;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarjetas> tarjetas;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Licencias> licencias;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String constraseña) {
        this.contraseña = constraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) {this.apellido = apellido;}

    public String getTelefono() {return telefono;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getDireccion() {return direccion;}

    public void setDireccion(String direccion) {this.direccion = direccion;}

    public String getNit() {return nit;}

    public void setNit(String nit) {this.nit = nit;}

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", constraseña='" + contraseña + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", NIT='" + nit + '\'' +
                '}';
    }
}