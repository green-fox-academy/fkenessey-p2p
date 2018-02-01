package com.greenfoxacademy.p2p.factories;

import com.greenfoxacademy.p2p.models.DTOs.ErrorMessageDTO;
import com.greenfoxacademy.p2p.models.Message;
import com.greenfoxacademy.p2p.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebFactory {

  public User createUser () {
    return new User();
  }

  public ErrorMessageDTO createErrorMessageDTO (String errorMessage) {
    return new ErrorMessageDTO(errorMessage);
  }

  public Message createInitialMessage() {
    return new Message("App", "Hi there! Submit your message using the send button!");
  }

  public Message createNewMessage() {
    return new Message();
  }

  public Message createNewMessageWithParameters(String username, String text) {
    return new Message(username, text);
  }

  public List<Message> createInitialMessageList() {
    List<Message> messageList = new ArrayList<>();
    messageList.add(createInitialMessage());
    return messageList;
  }
}
