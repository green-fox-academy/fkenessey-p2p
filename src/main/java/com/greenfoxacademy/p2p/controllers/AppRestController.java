package com.greenfoxacademy.p2p.controllers;

import com.greenfoxacademy.p2p.models.Client;
import com.greenfoxacademy.p2p.models.DTOs.MessageResponseListDTO;
import com.greenfoxacademy.p2p.models.DTOs.ReceiveDTO;
import com.greenfoxacademy.p2p.models.Message;
import com.greenfoxacademy.p2p.models.User;
import com.greenfoxacademy.p2p.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppRestController {

  @Autowired
  MessageService messageService;

  @CrossOrigin("*")
  @PostMapping("/api/message/receive")
  public ReceiveDTO receiveMessage(@RequestBody ReceiveDTO receiveDTO) {

    messageService.addMessageToList(receiveDTO.getMessage(), new User(receiveDTO.getMessage().getUsername()));
    return new ReceiveDTO(new Message(receiveDTO.getMessage().getUsername(), "Ok"));
  }

  @CrossOrigin("*")
  @GetMapping("/api/message/receive")
  public MessageResponseListDTO showMessages(@RequestBody Client client) {

    return new MessageResponseListDTO(messageService.getAllMessages(), client);
  }
}
