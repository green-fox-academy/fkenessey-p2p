package com.greenfoxacademy.p2p.models.DTOs;

import com.greenfoxacademy.p2p.models.Client;
import com.greenfoxacademy.p2p.models.Message;

public class ReceiveDTO {

  private Message message;
  private Client client;

  public ReceiveDTO() {
  }

  public ReceiveDTO(Message message) {
    this.message = message;
  }

  public Message getMessage() {
    return message;
  }

  public void setMessage(Message message) {
    this.message = message;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }
}
