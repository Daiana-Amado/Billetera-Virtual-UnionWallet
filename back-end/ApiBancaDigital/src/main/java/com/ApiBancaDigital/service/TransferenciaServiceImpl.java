package com.ApiBancaDigital.service;


import com.ApiBancaDigital.entity.Cuenta;
import com.ApiBancaDigital.entity.Transferencia;
import com.ApiBancaDigital.persistence.CuentaRepository;
import com.ApiBancaDigital.persistence.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TransferenciaServiceImpl  implements ITransferenciaService {

    @Autowired TransferenciaRepository transferenciaRepository;
    @Autowired CuentaRepository cuentaRepository;
    @Autowired CuentaServiceImpl cuentaService;


    @Override
    public List<Transferencia> getTransferenciaByOrigen(Long nroCuentaOrigen) {
        return transferenciaRepository.getTransferenciaByOrigen(nroCuentaOrigen);
    }

    @Override
    public List<Transferencia> getTransferenciaByDestino(Long nroCuentaDestino) {
        return transferenciaRepository.getTransferenciaByDestino(nroCuentaDestino);
    }

    @Override
    public List<Transferencia> getAllTransferenciaByCuenta(Long nroCuenta) {
        return transferenciaRepository.getAllTransferenciaByCuenta(nroCuenta);
    }

    @Transactional
    public Map<String,String> generateTrasnferencia(Long nroCuentaOrigen, Long nroCuentaDestino, double monto,String descripcion) {
        Map<String,String> response = new HashMap<>();

        double tolerance = 0.00001;
        if (Math.abs(nroCuentaOrigen - nroCuentaDestino) <= tolerance) {
            response.put("error", "Cuenta Origen y Cuenta Destino no pueden ser la misma");
        }else{
            Optional<Cuenta> cuentaOrigen = Optional.ofNullable(cuentaRepository.findByNumCuenta(nroCuentaOrigen));
            Optional<Cuenta> cuentaDestino = Optional.ofNullable(cuentaRepository.findByNumCuenta(nroCuentaDestino));
            Transferencia registrarTrasnferencia = //transferenciaRepository.save(
                    Transferencia.builder()
                            .monto(monto)
                            .cuentaBancariaOrigen(nroCuentaOrigen)
                            .cuentaBancariaDestino(nroCuentaDestino)
                            .tipoMoneda("USD")//Está definido en automático en el schema de la BD pero no lo toma por el Hibernate
                            .numReferencia(getNumReferenciaFromBD()) // estaba definido en un Trigger en la tabla, pero no retomaba con el saveAndFlush
                            .descripcion(descripcion)
                            .fechaEjecucion(new Date())
                            .build();
            if(!cuentaOrigen.isPresent()){
                response.put("error","número de cuenta Origen no encontrado");
            }
            if(!cuentaDestino.isPresent()){
                response.put("error","número de cuenta Destino no encontrado");
            }

            if(!response.containsKey("error")){
                if(cuentaOrigen.get().getSaldoTotal() < monto){
                    response.put("error","cuenta de origen no tiene saldo suficiente ");
                }else{
                    cuentaOrigen.get().setSaldoTotal(
                            cuentaOrigen.get().getSaldoTotal() - monto
                    );
                    cuentaOrigen.get().setPuntos(
                            cuentaOrigen.get().getPuntos() + ((int)monto/10)//Utilizar formula
                    );

                    cuentaDestino.get().setSaldoTotal(
                            cuentaDestino.get().getSaldoTotal() + monto
                    );
                    cuentaOrigen.get().getTransferenciasRealizadas().add(registrarTrasnferencia);
                    cuentaDestino.get().getTransferenciasRecibidas().add(registrarTrasnferencia);

                    response.put("status","Transferencia Exitosa");
                    response.put("referencia",registrarTrasnferencia.getNumReferencia().toString());
                }
            }


            /*responseDescuento.putAll(cuentaService.restarSaldo(nroCuentaOrigen,monto));

            if(responseDescuento.containsKey("error")){
                response.put("errorOrigen",responseDescuento.get("error"));
            }else{
                responseIncremento.putAll(cuentaService.sumarSaldo(nroCuentaDestino,monto));

                if(responseIncremento.containsKey("error")){
                    response.put("errorDestino",responseIncremento.get("error"));
                }

                if(!response.containsKey("errorOrigen") && !response.containsKey("errorDestino")){


                }
            }*/
        }

    return response;
    }

    @Override
    public List<Transferencia> getAllTransferenciaByFecha(Long nroCuenta, Date fechaInicio, Date fechaFin) {
        return transferenciaRepository.getAllTransferenciaByFecha(nroCuenta,fechaInicio,fechaFin);
    }



    @Override
    public Transferencia getTransferenciaByReferencia(String numReferencia) {
        return transferenciaRepository.getTransferenciaByReferencia(numReferencia);
    }

    @Override
    public Long getNumReferenciaFromBD() {
        Long valor = transferenciaRepository.getNumReferencia();
        if(valor == null || valor == 0){
            valor = 10000451L;
        }else{
            valor+=1;
        }
        return valor;
    }
}
