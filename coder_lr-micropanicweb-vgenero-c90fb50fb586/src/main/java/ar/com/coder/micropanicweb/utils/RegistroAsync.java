//package ar.com.coder.micropanicweb.utils;
//
//import ar.com.coder.micropanicweb.model.Usuario;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
///**
// * Creado por Ascari Q. Romo Pedraza - molder.itp@gmail.com on 09/04/17.
// */
//@Service
//public class RegistroAsync {
//
//    private MailSender mailSender;
//
//    @Autowired
//    public RegistroAsync(MailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    @Async
//    public void enviarCorreo(Usuario usuario) {
//        System.out.println("enviando correo ");
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        String msj;
//        mailMessage.setFrom("microman.notificaciones@gmail.com"); //remitente
//        mailMessage.setTo(usuario.getEmail());
//        mailMessage.setSubject("Registro completado");
//        msj = "Su registro fue completado con exito\n";
//        msj += "Usuario: " + usuario.getUsername()+"\n";
//        msj += "Clave:" + usuario.getClave();
//        String url = "<a href=\"http://panico.coder.com.ar:8080/micropanicweb/auth="+usuario.getHash()+"\">clic aqui</a>";
//        msj += "Codigo de Activacion: "+url;
//        
//        mailMessage.setText(msj);
//
//        mailSender.send(mailMessage);
//    }
//}
