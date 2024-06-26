package com.ApiBancaDigital.dto.Transferencia.request;

import com.ApiBancaDigital.advice.validation.anotation.MinMaxDigits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class TransferenciaByFechaDTO {
    @MinMaxDigits(min = 9)
    Long nroCuenta;

    @NotBlank
    String fechaInicial;
    @NotBlank
    String fechaFinal;
}
