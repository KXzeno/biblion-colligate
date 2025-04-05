package com.karnovah.biblion.utils;

public class TaggedSession {
  private String tag;
  private String sessionId;
  private int rate;

  public TaggedSession(String tag, int rate) {
    this.tag = tag;
    this.rate = rate;
  }

  public String getTag() {
    return this.tag;
  }

  public int getRate() {
    return this.rate;
  }

  public String getSessionId() {
    return this.sessionId;
  }

  public void setRate(int rate) {
    this.rate = rate;
  }

  public void setSessionId(String id) {
    this.sessionId = id;
  }
}
