package org.code_wizards.Gestion_Renta_Vehiculos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Tarjetas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarjetas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarjeta;
    private String pan;
    private String cvv;
    private String fechaVencimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ToString.Exclude
    private Cliente cliente;
}