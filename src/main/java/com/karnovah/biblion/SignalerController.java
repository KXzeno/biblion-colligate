package com.karnovah.biblion;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.util.HtmlUtils;

import com.karnovah.biblion.utils.ImpotentList;
import com.karnovah.biblion.utils.TaggedSession;;

@Controller
public class SignalerController {
  public int signalCount = 0;
  public int connections = 0;
  private ImpotentList sessions = new ImpotentList();

  @MessageMapping("/observe/{id}")
  @SendTo("/temp/audits/{id}")
  public Signaler signaler(@DestinationVariable String id, Observer message) throws Exception {
    Thread.sleep(1000); // simulated delay
    String msg = message.getSighted();

    try {
      TaggedSession session = sessions.find(id);

      if (session == null) {
        TaggedSession newSession = new TaggedSession(id, 0);
        sessions.add(newSession);
        session = newSession;
      }

      char lastChar = msg.charAt(msg.length() - 1);
      String rate = Character.toString(lastChar);
      String user = msg.substring(0, msg.length() - 2);
      int updatedRate = session.getRate() + Integer.parseInt(rate);

      session.setRate(updatedRate);

      return new Signaler(String.format("%s: %s-%s", user, connections, updatedRate));
    } catch (NumberFormatException exc) {
      System.out.printf("%s", exc);
      return new Signaler(String.format("%s%s: %s", "Received: ", HtmlUtils.htmlEscape(msg), "DOES NOT CONFORM TO DATA MODEL"));
    } finally {
      this.signalCount++;
    }
  }

  @EventListener
  public void handleConnectEvent(SessionConnectEvent event) {
    this.connections++;
  }

  @EventListener
  public void handleDisconnectEvent(SessionDisconnectEvent event) {
    switch (this.connections) {
      case 1: 
        this.signalCount = 0;
        this.connections--;
        break;
      case 0:
        System.out.printf("%s", "Fallback initiated.");
        this.signalCount = 0;
        this.connections = 0;
      default: this.connections--;
    }
  }
}
