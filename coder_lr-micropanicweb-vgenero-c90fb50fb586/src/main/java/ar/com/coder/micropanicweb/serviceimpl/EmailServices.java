package ar.com.coder.micropanicweb.serviceimpl;

import ar.com.coder.micropanicweb.model.Usuario;
import ar.com.coder.micropanicweb.utils.Mail;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service("emailService")
public class EmailServices {
    
    @Value("${emailFrom}")
    private String emailFrom;
    private String model;
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;
    
    @Async
    public void sendSimpleMessage(SimpleMailMessage email) {
        emailSender.send(email);
    }
    public Boolean sendSimpleMessage(Mail mail) throws MessagingException, IOException {

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(mail.getModel());
            String html = templateEngine.process(this.model, context);

            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());

            emailSender.send(message);
            return true;
        } catch (SendFailedException e) {
            Logger.getLogger(EmailServices.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean nuevoUsuario(Usuario user) {
        String hash;
        try {
            this.model = "email/crearUsuario"; //modelo html
            Mail mail = new Mail();
            //mail.setFrom("panicalarm@larioja.gov.ar");
            mail.setFrom(this.emailFrom);
            mail.setTo(user.getEmail());
            mail.setSubject("Cuenta de Usuario Panic Alarm");

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("nombre", user.getPersona().getRazonSocial());
            model.put("email", user.getEmail());
            model.put("username", user.getUsername());
            model.put("clave", user.getClave());

            hash = "http://panico.coder.com.ar:8080/micropanicweb/auth/" + user.getHash();
            model.put("hash", hash);
            mail.setModel(model);
            Boolean result = this.sendSimpleMessage(mail);
            return result;

        } catch (MessagingException | IOException ex) {
            Logger.getLogger(EmailServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
