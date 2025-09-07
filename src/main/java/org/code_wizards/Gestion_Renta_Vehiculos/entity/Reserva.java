package org.code_wizards.Gestion_Renta_Vehiculos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva;
    private String fechaInicio;
    private String fechaFin;
    private String costoTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @ToString.Exclude
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ToString.Exclude
    private Cliente cliente;
}