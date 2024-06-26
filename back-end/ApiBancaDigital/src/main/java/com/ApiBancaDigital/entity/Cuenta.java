package com.ApiBancaDigital.entity;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cuenta")
@Entity
public  class Cuenta {


    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cuenta_sequence"
    )
    @Column (name = "num_cuenta")
    private Long numCuenta;

    @Column (name = "tipo_cuenta")
    private String tipoCuenta;


    @Column (name = "saldo_total")
    private Double saldoTotal;
 
    @Column (name = "estado")
    private boolean estado;

    @CreationTimestamp
    private Date fechaApertura;

    int puntos;


    @OneToMany(
            cascade = CascadeType.PERSIST,
            orphanRemoval = false
    )
    @JoinColumn(name = "cuenta_bancaria_origen")
    Set<Transferencia> transferenciasRealizadas;

    @OneToMany(
            cascade = CascadeType.PERSIST,
            orphanRemoval = false
    )
    @JoinColumn(name = "cuenta_bancaria_destino")
    Set<Transferencia> transferenciasRecibidas;

}
