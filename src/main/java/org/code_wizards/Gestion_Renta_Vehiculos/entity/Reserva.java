package org.code_wizards.Gestion_Renta_Vehiculos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private Cliente cliente;
}