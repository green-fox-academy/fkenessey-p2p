package com.greenfoxacademy.p2p.factories;

import com.greenfoxacademy.p2p.models.DTOs.ErrorMessageDTO;
import com.greenfoxacademy.p2p.models.User;
import org.springframework.stereotype.Component;

@Component
public class WebFactory {

  public User createUser () {
    return new User();
  }

  public ErrorMessageDTO createErrorMessageDTO (String errorMessage) {
    return new ErrorMessageDTO(errorMessage);
  }
}
