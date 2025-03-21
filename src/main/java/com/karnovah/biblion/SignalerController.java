package com.karnovah.biblion;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class SignalerController {

  @MessageMapping("/observe")
  @SendTo("/temp/audits")
  public Signaler signaler(Observer message) throws Exception {
    Thread.sleep(1000); // simulated delay
    return new Signaler("Received: " + HtmlUtils.htmlEscape(message.getSighted()));
  }
}
