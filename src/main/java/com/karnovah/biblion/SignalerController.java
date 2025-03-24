package com.karnovah.biblion;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class SignalerController {
  public int signalCount = 0;
  @MessageMapping("/observe/{id}")
  @SendTo("/temp/audits/{id}")
  public Signaler signaler(@DestinationVariable String id, Observer message) throws Exception {
    this.signalCount++;
    Thread.sleep(1000); // simulated delay
    return new Signaler(String.format("%s%s %s", "Received: ", HtmlUtils.htmlEscape(message.getSighted()), signalCount);
  }
}
