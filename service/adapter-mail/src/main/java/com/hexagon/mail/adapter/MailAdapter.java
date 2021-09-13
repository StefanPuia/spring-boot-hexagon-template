package com.hexagon.mail.adapter;

import com.hexagon.mail.MailPort;
import com.hexagon.model.Order;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailAdapter implements MailPort {

  private final MailSender mailSender;

  public MailAdapter(final MailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void sendOrderMail(final Order order) {

    final var mailMessage = new SimpleMailMessage();
    mailMessage.setFrom("me@hexagon.com");
    mailMessage.setTo("you@hexagon.com");
    mailMessage.setSubject("Test email");
    mailMessage.setText("hey! it's me");
    mailSender.send(mailMessage);
  }
}
