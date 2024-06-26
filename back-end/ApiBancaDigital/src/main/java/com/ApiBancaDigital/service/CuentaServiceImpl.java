package com.ApiBancaDigital.service;


import com.ApiBancaDigital.entity.Cuenta;
import com.ApiBancaDigital.entity.User;
import com.ApiBancaDigital.persistence.CuentaRepository;
import com.ApiBancaDigital.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements  ICuentaService{

    @Autowired CuentaRepository cuentaRepository;

    @Autowired UserRepository userRepository;

    @Override
    public List<Cuenta> getCuenta() {
        return (List<Cuenta>)cuentaRepository.findAll();
    }

    @Override
    public Cuenta findCuentaByNumCuenta(Long numCuenta) {
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(numCuenta);
        if(!optionalCuenta.isPresent()){
            throw new IllegalStateException("La cuenta con numero: " + numCuenta + " no existe!");
        }
        return cuentaRepository.findByNumCuenta(numCuenta);
    }

    @Override
    public Cuenta createCuenta(String userName) {
        User user = userRepository.findByUserName(userName);
        if( user == null){
            throw new IllegalStateException("El usuario con nombre de usuario: " + userName + " no existe!");
        }

        List<Cuenta> cuentatList = user.getCuentas();
        Cuenta newCuenta = Cuenta.builder()
                .saldoTotal(1000.0)
                .tipoCuenta("basic")
                .puntos((int) (Math.random()*75))//Post presentación este valor debe ser 0
                .fechaApertura(new Date())
                .estado(true).build();
        cuentatList.add(newCuenta);
        user.setCuentas(cuentatList);
        userRepository.save(user);

        return cuentaRepository.save(newCuenta);
    }

    @Override
    public void deleteCuenta(Long numCuenta, String userName) {
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(numCuenta);
        if(optionalCuenta.isEmpty()){
            throw new IllegalStateException("La cuenta con numero: " + numCuenta + " no existe!");
        }

        User user = userRepository.findByUserName(userName);
        if( user == null){
            throw new IllegalStateException("El usuario con nombre de usuario: " + userName + " no existe!");
        }

        Cuenta cuentaToDelete = optionalCuenta.get();

        user.getCuentas().remove(cuentaToDelete);

        userRepository.save(user);

        cuentaRepository.delete(cuentaToDelete);
    }

}
