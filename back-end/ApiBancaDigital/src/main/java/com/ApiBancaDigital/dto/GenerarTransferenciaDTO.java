package com.ApiBancaDigital.dto;

import com.ApiBancaDigital.advice.validation.anotation.MinMaxDigits;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerarTransferenciaDTO {

    @MinMaxDigits(min = 9) Long nroCuentaOrigen;
    @MinMaxDigits(min = 9) Long nroCuentaDestino;
    @Min(1) double monto;
    String descripcion;
}
