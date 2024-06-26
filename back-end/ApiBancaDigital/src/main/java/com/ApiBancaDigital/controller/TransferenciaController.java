package com.ApiBancaDigital.controller;


import com.ApiBancaDigital.advice.validation.anotation.MinMaxDigits;
import com.ApiBancaDigital.dto.GenerarTransferenciaDTO;
import com.ApiBancaDigital.dto.Transferencia.request.NroCuentaDTO;
import com.ApiBancaDigital.dto.Transferencia.request.TransferenciaByFechaDTO;
import com.ApiBancaDigital.dto.TransferenciaDTO;
import com.ApiBancaDigital.entity.Transferencia;
import com.ApiBancaDigital.mapper.TransferenciaMapper;
import com.ApiBancaDigital.service.ITransferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/transferencia")
@CrossOrigin(origins = "*")
public class TransferenciaController {


    @Autowired private ITransferenciaService iTransferenciaService;

    @PostMapping(value ="/transferir" )
    @Operation(summary = "EndPoint para generar una transferencia debe enviar: nroCuenta Origen, nroCuenta Destino y el monto a enviar")
    public Map<String,String> createTransferencia(
           @RequestBody  @Valid GenerarTransferenciaDTO transferenciaDTO
    ) {
        Map<String,String> response = new HashMap<>();
        response.putAll(
                iTransferenciaService.generateTrasnferencia(
                        transferenciaDTO.getNroCuentaOrigen(),
                        transferenciaDTO.getNroCuentaDestino(),
                        transferenciaDTO.getMonto(),
                        transferenciaDTO.getDescripcion()
                )
        );

        return response;
    }

    @PostMapping("/byCuentaOrigen")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Enviando el nro de la cuenta bancaria Origen recibe todas las transferencias Creadas.")
    public ResponseEntity<List<Transferencia>> getTransferenciasBynroCuentaOrigen(
            @RequestBody @Valid NroCuentaDTO cuentaOrigen){
        return ResponseEntity.ok(
          iTransferenciaService.getTransferenciaByOrigen(cuentaOrigen.getNroCuenta())
        );
    }

    @PostMapping("/byCuentaDestino")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Enviando el nro de la cuenta bancaria Destino recibe todas las transferencias enviadas a.")
    public ResponseEntity<List<Transferencia>> getTransferenciasBynroCuentaDestino(
            @RequestBody @Valid NroCuentaDTO cuentaDestino){
        return ResponseEntity.ok(
          iTransferenciaService.getTransferenciaByDestino(cuentaDestino.getNroCuenta())
        );
    }

    @PostMapping("/byCuenta")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Enviando el nro de la cuenta bancaria  recibe todas las transferencias enviadas o recibidas")
    public ResponseEntity<List<Transferencia>> getTransferenciasBynroCuenta(
            @RequestBody @Valid NroCuentaDTO cuentaDTO){
        return ResponseEntity.ok(
                iTransferenciaService.getAllTransferenciaByCuenta(cuentaDTO.getNroCuenta())
        );
    }

    @PostMapping("/byRangoFecha")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Enviando nroCuenta y un rango de fechas (Inicial y Final) recibe un listado de todas las trasnferencias realizadas o recibidas ")
    public ResponseEntity<List<Transferencia>> getTransferenciasByRangoFecha(
            @RequestBody @Valid TransferenciaByFechaDTO transferenciaByFechaDTO
    ){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date dFechaInicial = new Date();
        Date dFechaFinal = new Date();
        try {
             dFechaInicial = formatter.parse(transferenciaByFechaDTO.getFechaInicial()+" 00:00:00");
            dFechaFinal = formatter.parse(transferenciaByFechaDTO.getFechaFinal()+" 23:59:59");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(
                iTransferenciaService.getAllTransferenciaByFecha(transferenciaByFechaDTO.getNroCuenta(),dFechaInicial,dFechaFinal)
        );
    }




}
