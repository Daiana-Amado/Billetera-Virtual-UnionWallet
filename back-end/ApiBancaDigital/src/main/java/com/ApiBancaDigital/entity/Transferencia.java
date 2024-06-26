package com.ApiBancaDigital.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transferencia")
@Entity
public class Transferencia {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "tipo_moneda")
    private  String tipoMoneda;

    @Column(name = "cuenta_bancaria_origen")
    private Long cuentaBancariaOrigen;

    @Column(name = "cuenta_bancaria_destino")
    private Long cuentaBancariaDestino;

    private Double monto;

    @Column(name = "num_referencia")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long numReferencia;

    String descripcion;

    @CreationTimestamp
    private Date fechaEjecucion;


}
