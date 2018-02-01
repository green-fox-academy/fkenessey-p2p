package com.greenfoxacademy.p2p.models;

public class Client {

  private String id;

  public Client() {
    this.id = System.getenv("CHAT_APP_UNIQUE_I");
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
