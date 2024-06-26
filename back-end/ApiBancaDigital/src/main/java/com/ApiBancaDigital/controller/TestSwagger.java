package com.ApiBancaDigital.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/swagger")
@CrossOrigin(origins = "*")
public class TestSwagger {




    @GetMapping("/test")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation (summary = "Aqui debe ir la explicación del endPoint")
    public ResponseEntity<?> testEndPointSwagger(@PathVariable String meesage){
        return ResponseEntity.ok(meesage);
    }
}
