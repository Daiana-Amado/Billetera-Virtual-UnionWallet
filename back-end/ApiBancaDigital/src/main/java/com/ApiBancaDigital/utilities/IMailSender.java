
package com.ApiBancaDigital.utilities;

import org.springframework.stereotype.Component;

@Component
public interface IMailSender {
    
    public void enviarCorreo(String destinatario, String asunto, String mensaje);
    
    public void correoBienvenida(String destinatario);
    
    public void correoTransferencia(Long nroCuentaOrigen, Long nroCuentaDestino, double monto, String numReferencia, String correoElectronico);
   
}

