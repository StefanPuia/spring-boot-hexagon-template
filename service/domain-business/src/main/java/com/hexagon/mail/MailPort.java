package com.hexagon.mail;

import com.hexagon.model.Order;

public interface MailPort {

  void sendOrderMail(Order order);
}
