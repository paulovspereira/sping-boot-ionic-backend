package com.pv.cursomc.service;

import org.springframework.mail.SimpleMailMessage;

import com.pv.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
