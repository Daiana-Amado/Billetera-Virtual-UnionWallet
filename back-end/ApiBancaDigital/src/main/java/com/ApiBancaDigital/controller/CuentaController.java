package com.ApiBancaDigital.controller;

import com.ApiBancaDigital.dto.CuentaDTO;
import com.ApiBancaDigital.entity.Cuenta;
import com.ApiBancaDigital.mapper.CuentaMapper;
import com.ApiBancaDigital.service.ICuentaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cuenta")
@RequiredArgsConstructor
public class CuentaController {

    @Autowired private ICuentaService iCuentaService;

    @GetMapping("/listAll")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary="Lista de todas las cuentas existentes")
    public ResponseEntity<List<Cuenta>> getListCuenta(){
        return ResponseEntity.ok(
                iCuentaService.getCuenta()
                );
    }

    @GetMapping("/{numCuenta}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary="Retorna, si existe, la cuenta con el numero de cuenta deseado")
    public ResponseEntity<Cuenta> getCuentaByNumCuenta(@PathVariable("numCuenta") Long numCuenta){
        return ResponseEntity.ok(
                iCuentaService.findCuentaByNumCuenta(numCuenta)
        );
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary="Crea una nueva cuenta")
    public ResponseEntity<Cuenta> createCuenta(@Min(1) @RequestParam String userName) {
        return ResponseEntity.ok(
                iCuentaService.createCuenta(userName)
        );
    }

    @DeleteMapping("/{numCuenta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary="Elimina, si existe, la cuenta con el numero de cuenta deseado")
    public void deleteCuenta(@PathVariable("numCuenta") Long numCuenta, @RequestParam String userName){
        iCuentaService.deleteCuenta(numCuenta, userName);
    }
}
