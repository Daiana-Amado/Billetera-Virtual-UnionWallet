package com.ApiBancaDigital.service;

import com.ApiBancaDigital.entity.Cuenta;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ICuentaService {

    List<Cuenta> getCuenta();

    Cuenta findCuentaByNumCuenta(Long numCuenta);

    Cuenta createCuenta(String userName);

    void deleteCuenta(Long numCuenta, String userName);


}