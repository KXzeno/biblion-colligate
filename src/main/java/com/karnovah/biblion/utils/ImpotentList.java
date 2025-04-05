package com.karnovah.biblion.utils;

public class ImpotentList {
  private SessionNode first = null;
  private SessionNode last = null;
  private int size = 0;

  private class SessionNode {
    TaggedSession data;
    SessionNode prev;
    SessionNode next;

    private SessionNode(TaggedSession data, SessionNode prev, SessionNode next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }

    private TaggedSession getData() {
      return this.data;
    }

    private SessionNode getNext() {
      return this.next;
    }

    private SessionNode getPrev() {
      return this.prev;
    }

    private void setNext(SessionNode next) {
      this.next = next;
    }

    private void setPrev(SessionNode prev) {
      this.prev = prev;
    }

    private void setData(TaggedSession data) {
      this.data = data;
    }
  }

  public ImpotentList(TaggedSession session) {
    this.add(session);
    this.size++;
  }

  public ImpotentList() {
    this.first = new SessionNode(null, null, last);
    this.last = new SessionNode(null, this.first, null);
  }

  public void add(TaggedSession session) {
    if (this.size == 0) {
      this.first = new SessionNode(null, null, last);
      this.last = new SessionNode(null, this.first, null);
      SessionNode newNode = new SessionNode(session, this.first, this.last);
      this.first.setNext(newNode);
      this.last.setPrev(newNode);
      this.size++;
      return;
    }

    SessionNode tail = last.getPrev();
    SessionNode newNode = new SessionNode(session, tail, last);
    tail.setNext(newNode);
    this.size++;
  }

  public TaggedSession find(String id) {
    if (this.size == 0) {
      return null;
    }

    SessionNode traverser = first.getNext();

    int count = 1;

    if (traverser == this.last) {
      return null;
    }

    while (traverser != this.last) {
      TaggedSession data = traverser.getData();
      String tag = data.getTag();
      if (tag.equals(id)) {
        return traverser.getData();
      }

      traverser = traverser.getNext();
      count++;
    }

    return traverser.getData();
  }

  public TaggedSession find(int position) {
    if (position > this.size) {
      return null;
    }

    int count = 1;
    SessionNode traverser = first;
    while (count != position) {
      traverser = traverser.getNext();
      count++;
    }

    return traverser.data;
  }

  private SessionNode findNode(String id) {
    SessionNode traverser = first;
    int count = 1;
    while (traverser.data.getTag() != id && count != this.size) {
      traverser = traverser.getNext();
      count++;
      if (count == this.size) {
        return null;
      }
    }

    String tag = traverser.data.getTag();
    if (tag == id) {
      return traverser;
    }
    return null;
  }
  public void remove(String id) {
    SessionNode target = this.findNode(id);
    SessionNode beforeTarget = target.getPrev();
    SessionNode afterTarget = target.getNext();
    beforeTarget.setNext(afterTarget);
    afterTarget.setPrev(beforeTarget);
    target.setPrev(null);
    target.setNext(null);
    target = null;
    this.size--;
  }
}
