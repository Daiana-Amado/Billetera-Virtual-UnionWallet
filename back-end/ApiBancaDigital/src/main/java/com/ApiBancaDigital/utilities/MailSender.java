package com.ApiBancaDigital.utilities;

import com.ApiBancaDigital.dto.PersonDTO;
import com.ApiBancaDigital.service.PersonService;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailSender implements IMailSender{

    @Autowired 
    private PersonService persoServ;
    
    //MÉTODO DE ENVÍO DE CORREOS DESDE UNION WALLET A USUARIOS
    @Override
    public void enviarCorreo(String destinatario, String asunto, String mensaje) {
        try {
            // Configuración del servidor SMTP
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("avisos.unionbank@gmail.com", "bfaenpfhvozcxjrx");
                }
            });

            Message message = new MimeMessage(session);

            InternetAddress remitente = new InternetAddress("avisos.unionbank@gmail.com");

            message.setFrom(remitente);

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(mensaje);

            // Enviar el correo electrónico
            Transport.send(message);

        } catch (MessagingException me) {
            me.printStackTrace();

        }
    }

    //MÉTODO DE ENVÍO DE CORREO DE BIENVENIDA PARA USUARIOS RECIÉN REGISTRADOS
    @Override
    public void correoBienvenida(String destinatario) {

        List<PersonDTO> listaPersonas = persoServ.getPersons().getBody().getObj();
        
        PersonDTO personaEncontrada = new PersonDTO();
        
        for(PersonDTO persona : listaPersonas){
            if(persona.getEmail().equals(destinatario)){
                personaEncontrada = persona;
            }
        }      
        
        String asunto = "¡Bienvenido/a a Union Wallet!";
        String cuerpo = "Estimado/a " + personaEncontrada.getName()
                      + "\\n¡Bienvenido/a a nuestra plataforma! Esperamos que disfrutes de nuestros servicios."
                      + "Atentamente, el equipo de Union Wallet";


        enviarCorreo(personaEncontrada.getEmail(), asunto, cuerpo);

    }
    
    //MÉTODO PARA ENVÍO DE CORREOS PARA NOTIFICAR CADA TRANSFERENCIA
    @Override
    public void correoTransferencia(Long nroCuentaOrigen, Long nroCuentaDestino, double monto, String numReferencia, String correoElectronico) {
        
        String asunto = "Transferencia realizada correctamente";
        String cuerpo = "Hola! Union Wallet te informa que la transferencia realizada desde el remitente:  "
                + nroCuentaOrigen + "\n" + "Al destinatario: " + nroCuentaDestino + "\n" 
                + "Con el monto: $" + monto + "\n"
                + "Fue realizada correctamente! \n" + "\n" + "Atentamente, el equipo de Union Wallet. \n" + "\n"
                + "Número de referencia de tu transferencia: " + numReferencia;

        enviarCorreo(correoElectronico, asunto, cuerpo);

    }
}
