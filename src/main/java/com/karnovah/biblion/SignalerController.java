package com.karnovah.biblion;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class SignalerController {

  @MessageMapping("/observe/{id}")
  @SendTo("/temp/audits/{id}")
  public Signaler signaler(@DestinationVariable String id, Observer message) throws Exception {
    Thread.sleep(1000); // simulated delay
    return new Signaler("Received: " + HtmlUtils.htmlEscape(message.getSighted()));
  }
}
