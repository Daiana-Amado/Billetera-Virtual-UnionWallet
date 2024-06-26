package com.ApiBancaDigital.dto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.json.JSONPropertyName;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDTO {


    private Long id;

    @Min(1)
    private Long cuentaBancariaOrigen;

    @Min(1)
    private Long cuentaBancariaDestino;

    @Min(1)
    private Double monto;

    @NotNull
    @NotBlank
    private Long numReferencia;

    private Date fechaEjecucion;

    String descripcion;

}
