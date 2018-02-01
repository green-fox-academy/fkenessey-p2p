package com.greenfoxacademy.p2p.services;

import com.greenfoxacademy.p2p.factories.WebFactory;
import com.greenfoxacademy.p2p.models.Message;
import com.greenfoxacademy.p2p.models.User;
import com.greenfoxacademy.p2p.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

  @Autowired
  MessageRepository messageRepository;
  @Autowired
  WebFactory webFactory;

  public List getAllMessages() {
    List<Message> messageList = new ArrayList<>();
    messageRepository.findAll().forEach(messageList::add);
    return messageList;
  }

  public boolean ifMessageExist() {
    return !(getAllMessages().isEmpty());
  }

  public void createInitialMessage() {
    messageRepository.save(webFactory.createInitialMessage());
  }

  public List<Message> createNewMessageList() {
    createInitialMessage();
    return webFactory.createInitialMessageList();
  }

  public boolean isEnteredMessageNull(Message message) {
    return message.getText().isEmpty();
  }

  public void addMessageToList(Message message, User user) {
    messageRepository.save(webFactory.createNewMessageWithParameters(user.getUsername(), message.getText()));
  }
}
