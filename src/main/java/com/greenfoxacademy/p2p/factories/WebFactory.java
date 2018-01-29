package com.greenfoxacademy.p2p.factories;

import com.greenfoxacademy.p2p.models.User;
import org.springframework.stereotype.Component;

@Component
public class WebFactory {

  public User createUser () {
    User user = new User();
    return user;
  }
}
