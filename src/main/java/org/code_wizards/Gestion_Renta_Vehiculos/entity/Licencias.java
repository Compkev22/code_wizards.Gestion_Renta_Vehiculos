package org.code_wizards.Gestion_Renta_Vehiculos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Licencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Licencias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLicencia;
    private String tipo;
    private String nombre;
    private String apellido;
    private String fechaVencimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ToString.Exclude
    private Cliente cliente;
}