package com.ApiBancaDigital.persistence;

import com.ApiBancaDigital.entity.Cuenta;
import com.ApiBancaDigital.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    @Query("SELECT c FROM Cuenta c WHERE c.numCuenta = :numCuenta")
    Cuenta findByNumCuenta(@Param("numCuenta")Long numCuenta);
}