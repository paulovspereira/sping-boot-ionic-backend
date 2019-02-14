package com.pv.cursomc.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.pv.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{
	
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

   protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
	SimpleMailMessage sm = new SimpleMailMessage();
	sm.setTo(obj.getCliente().getEmail());
	sm.setFrom(sender);
	sm.setSubject("Pedido confirmado! Código: " +obj.getId());
	sm.setSentDate(new Date(System.currentTimeMillis()));
	sm.setText(obj.toString());
	return sm;
   }
   
   protected String htmlFromTemplatePedido(Pedido obj) {
	   Context context = new Context();
	   context.setVariable("Pedido", obj);
	   return templateEngine.process("email/confirmacaoPedido", context);
	   
	   
   }
   
   @Override
   public void sendOrderConfirmationHtmlEmail(Pedido obj) {
	   try {
	   MimeMessage nm = prepareSimpleMimeMessageFromPedido(obj);
		sendHtmlEmail(nm);
	   }
	   catch (MessagingException e) {
		sendOrderConfirmationEmail(obj);
	}
		
   }

   protected MimeMessage prepareSimpleMimeMessageFromPedido(Pedido obj) throws MessagingException {
      MimeMessage mime = javaMailSender.createMimeMessage();
      MimeMessageHelper mmh = new MimeMessageHelper(mime, true);
      mmh.setTo(obj.getCliente().getEmail());
      mmh.setFrom(sender);
      mmh.setSubject("Pedio confirmado Código: " + obj.getId());
      mmh.setSentDate(new Date(System.currentTimeMillis()));
      mmh.setText(htmlFromTemplatePedido(obj), true);
      return mime;
}
}
