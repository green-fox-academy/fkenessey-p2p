package com.greenfoxacademy.p2p.services;

import com.greenfoxacademy.p2p.factories.WebFactory;
import com.greenfoxacademy.p2p.models.DTOs.ErrorMessageDTO;
import com.greenfoxacademy.p2p.models.User;
import com.greenfoxacademy.p2p.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

  @Autowired
  WebFactory webFactory;
  @Autowired
  UserRepository userRepository;

  public User createNewUser() {
    return webFactory.createUser();
  }

  public List<User> getAllUsers() {
    List<User> userFullList = new ArrayList<>();
    userRepository.findAll().forEach(userFullList::add);
    return userFullList;
  }

  public boolean saveUserIfNameNotInTheRepository(User user) {
    if (userRepository.findAllByUsername(user.getUsername()).isEmpty()) {
      userRepository.save(user);
      return true;
    } else {
      return false;
    }
  }

  public boolean ifUserExist() {
    return !(getAllUsers().isEmpty());
  }

  public User updateUser(User user) {
    userRepository.findOne(user.getId()).setUsername(user.getUsername());
    userRepository.save(userRepository.findOne(user.getId()));
    return userRepository.findOne(user.getId());
  }

  public boolean isEnteredUserNotNull(User user) {
    return !(user.getUsername().isEmpty());
  }

  public ErrorMessageDTO sendError(String errorMessage) {
    return webFactory.createErrorMessageDTO(errorMessage);
  }
}
