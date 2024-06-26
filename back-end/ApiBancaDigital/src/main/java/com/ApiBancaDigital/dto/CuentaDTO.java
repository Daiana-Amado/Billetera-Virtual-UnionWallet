package com.ApiBancaDigital.dto;


import com.ApiBancaDigital.entity.Transferencia;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {

    @NotNull
    private Long numCuenta;

    @NotNull
    private String tipoCuenta;

    @NotNull
    private Double saldoTotal;

    @NotNull
    private boolean estado;

    private LocalDate fechaApertura;

    Set<TransferenciaDTO> transferencias;
}