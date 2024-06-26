package com.ApiBancaDigital.service;


import com.ApiBancaDigital.entity.Transferencia;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ITransferenciaService {

    List<Transferencia> getTransferenciaByOrigen(Long nroCuentaOrigen);

    List<Transferencia> getTransferenciaByDestino(Long nroCuentaDestino);

    List<Transferencia> getAllTransferenciaByCuenta(Long nroCuenta);


    Map<String,String> generateTrasnferencia(
            Long nroCuentaOrigen,
            Long nroCuentaDestino,
            double monto,
            String descripcion

    );

    List<Transferencia> getAllTransferenciaByFecha(Long nroCuenta, Date fechaInicio, Date fechaFin);

    Transferencia getTransferenciaByReferencia (String numReferencia);

    Long getNumReferenciaFromBD();


}
