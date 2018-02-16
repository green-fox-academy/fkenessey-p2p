package com.greenfoxacademy.p2p.models.DTOs;

import com.greenfoxacademy.p2p.models.Client;
import com.greenfoxacademy.p2p.models.Message;

import java.util.List;

public class MessageResponseListDTO {

  private List<Message> messages;
  private Client client;

  public MessageResponseListDTO() {
  }

  public MessageResponseListDTO(List<Message> messages, Client client) {
    this.messages = messages;
    this.client = client;
  }

  public List<Message> getMessages() {
    return messages;
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }
}
