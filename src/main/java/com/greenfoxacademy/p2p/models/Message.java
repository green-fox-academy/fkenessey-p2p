package com.greenfoxacademy.p2p.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "message_table")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String username;
  private String text;
  private Timestamp timestamp;

  public Message() {
    this.timestamp = Timestamp.from(Instant.now());
  }

  public Message(String username, String text) {
    this.username = username;
    this.text = text;
    this.timestamp = Timestamp.from(Instant.now());
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }
}
