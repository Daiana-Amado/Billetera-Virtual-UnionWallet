package com.ApiBancaDigital.dto.Transferencia.request;

import com.ApiBancaDigital.advice.validation.anotation.MinMaxDigits;
import lombok.Data;

@Data
public class NroCuentaDTO {
    @MinMaxDigits(min = 9)
    Long nroCuenta;
}
