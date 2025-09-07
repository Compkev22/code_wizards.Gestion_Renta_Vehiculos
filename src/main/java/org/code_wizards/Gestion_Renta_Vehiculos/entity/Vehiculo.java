package org.code_wizards.Gestion_Renta_Vehiculos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "Vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matricula;
    private String marca;
    private String modelo;
    private String año;
    private String tipo;
    private Integer precioDiario;
    private String disponibilidad;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Reserva> reservas;
}