package com.ApiBancaDigital.persistence;

import com.ApiBancaDigital.entity.Transferencia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia,Long> {


    @Query("SELECT t FROM Transferencia  t WHERE t.numReferencia = :numReferencia ")
    Transferencia getTransferenciaByReferencia(@Param("numReferencia")String numReferencia);

    @Query ("SELECT t FROM Transferencia t  WHERE t.fechaEjecucion BETWEEN :fechaInicio AND :fechaFinal AND (t.cuentaBancariaDestino = :nroCuenta OR t.cuentaBancariaOrigen = :nroCuenta)")
    List<Transferencia> getAllTransferenciaByFecha (
            @Param("nroCuenta") Long nroCuenta,
            @Param("fechaInicio") Date fechaInicial,
            @Param("fechaFinal") Date fechaFinal
    );

    @Query ("SELECT t FROM Transferencia t WHERE t.cuentaBancariaDestino = :nroCuenta OR t.cuentaBancariaOrigen = :nroCuenta")
    List<Transferencia> getAllTransferenciaByCuenta(@Param("nroCuenta") Long nroCuenta);

    @Query ("SELECT t FROM Transferencia t WHERE t.cuentaBancariaDestino = :nroCuentaDestino ")
    List<Transferencia> getTransferenciaByDestino(@Param("nroCuentaDestino") Long nroCuentaDestino);

    @Query ("SELECT t FROM Transferencia t WHERE t.cuentaBancariaOrigen = :nroCuentaOrigen  ")
    List<Transferencia> getTransferenciaByOrigen(@Param("nroCuentaOrigen") Long  nroCuentaOrigen);

    @Query("SELECT t.numReferencia from Transferencia t order by t.id desc limit 1")
    Long getNumReferencia();

}
