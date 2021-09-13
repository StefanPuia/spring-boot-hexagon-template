package com.hexagon.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.hexagon.dto.OrderDto;
import com.hexagon.mail.MailPort;
import com.hexagon.model.ImmutableOrder;
import com.hexagon.model.Order;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order", produces = APPLICATION_JSON_VALUE)
public class OrderController {

  private final MailPort mailPort;
  private final ConversionService conversionService;

  public OrderController(final MailPort mailPort,
      final ConversionService conversionService) {
    this.mailPort = mailPort;
    this.conversionService = conversionService;
  }

  private Order makeDummyOrder() {
    return ImmutableOrder.builder().setProductId("AB123").setPrice(10.6).build();
  }

  @GetMapping
  public ResponseEntity<OrderDto> getOrder() {
    return ResponseEntity.ok(conversionService.convert(makeDummyOrder(), OrderDto.class));
  }

  @GetMapping("/mail")
  public ResponseEntity<String> mailOrder() {
    try {
      mailPort.sendOrderMail(makeDummyOrder());
      return ResponseEntity.ok("Mail sent!");
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(ExceptionUtils.getStackTrace(e));
    }
  }
}
