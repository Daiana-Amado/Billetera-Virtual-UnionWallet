package com.ApiBancaDigital.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.ApiBancaDigital.utilities.MailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mensajes")
@CrossOrigin(origins = "*")
public class MailSenderController {

    @Autowired MailSender correo;
    
    @PostMapping("/prueba/{destinatario}/{asunto}/{mensaje}")
    @Operation(summary = "Permite envíar un correo de pruebas")
    public String mensajePrueba(@PathVariable String destinatario, @PathVariable String asunto, @PathVariable String mensaje){
        correo.enviarCorreo(destinatario, asunto, mensaje);
        return "Mensaje enviado correctamente";
    }
    
    @PostMapping("/bienvenida/{destinatario}")
    @Operation(summary = "Mensaje de bienvenida, el usuario debe estar registrado")
    public String mensajeBienvenida(@PathVariable String destinatario){
        correo.correoBienvenida(destinatario);
        return "Mensaje enviado correctamente";
    }
    
    @PostMapping("/transferencia/{nroCuentaOrigen}/{nroCuentaDestino}/{monto}/{numReferencia}/{correoElectronico}")
    @Operation(summary = "Mensaje de transferencia")
    public String mensajeTransferencia(@PathVariable Long nroCuentaOrigen, @PathVariable Long nroCuentaDestino,
            @PathVariable double monto, @PathVariable String numReferencia, @PathVariable String correoElectronico) {
        correo.correoTransferencia(nroCuentaOrigen, nroCuentaDestino, monto, numReferencia, correoElectronico);
        return "Notificación de transferencia enviada correctamente";
    }
   
}
