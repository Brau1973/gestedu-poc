package com.example.pruebaconcepto.services;

import com.example.pruebaconcepto.dtos.EmailValuesDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    @Value("${mail.urlFront}")
    private String urlFront;

    public void sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("silviajanet@gmail.com");
        msg.setTo("silviajanet@gmail.com");
        msg.setSubject("Prueba de concepto");
        msg.setText("Hola, este es un mensaje de prueba de concepto");

        javaMailSender.send(msg);
    }

//    public void sendEmailTemplate() {
//        MimeMessage message = javaMailSender.createMimeMessage();
//        try{
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            Context context = new Context();
//            String  htmlText = templateEngine.process("emailPass", context);
//            helper.setFrom("silviajanet@gmail.com");
//            helper.setTo("silviajanet@gmail.com");
//            helper.setSubject("Prueba de concepto");
//            helper.setText(htmlText, true);
//            javaMailSender.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }

    public void sendEmailTemplate(EmailValuesDTO dto) {
        MimeMessage message = javaMailSender.createMimeMessage();

        System.out.println(dto.getJwt());
        System.out.println(urlFront+ dto.getJwt());

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("username", dto.getUsername());
            model.put("url", urlFront + dto.getJwt());
            context.setVariables(model);
            String  htmlText = templateEngine.process("emailPass", context);
            helper.setFrom(dto.getMailFrom());
            helper.setTo(dto.getMailTo());
            helper.setSubject(dto.getMailSubject());
            helper.setText(htmlText, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
