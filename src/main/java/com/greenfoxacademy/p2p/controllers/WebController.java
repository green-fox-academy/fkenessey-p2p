package com.greenfoxacademy.p2p.controllers;

import com.greenfoxacademy.p2p.models.DTOs.ErrorMessageDTO;
import com.greenfoxacademy.p2p.models.User;
import com.greenfoxacademy.p2p.services.MessageService;
import com.greenfoxacademy.p2p.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

  @Autowired
  UserService userService;
  @Autowired
  MessageService messageService;

  @GetMapping("/")
  public String loadMainPage(Model model) {

    if (userService.ifUserExist()) {
      model.addAttribute("user", userService.getAllUsers().get(0));
      model.addAttribute("errorDto", userService.sendError(""));
      return "main";
    } else {
      return "redirect:/enter";
    }

  }

  @PostMapping("/")
  public String updateMainPage(@ModelAttribute(value = "user") User user,
                               Model model) {
    if (userService.isEnteredUserNotNull(user)) {
      model.addAttribute("user", userService.updateUser(user));
      model.addAttribute("errorDto", userService.sendError(""));
    } else {
      model.addAttribute("errorDto", userService
              .sendError("The username field is empty"));
    }
    return "main";
  }

  @GetMapping("/enter")
  public String enterUser(Model model) {
    if (userService.ifUserExist()) {
      return "redirect:/";
    } else {
      model.addAttribute("user", userService.createNewUser());
      model.addAttribute("errorDto", userService.sendError(""));
      return "enter";
    }

  }

  @PostMapping("/enter")
  public String addUser(@ModelAttribute(value = "user") User user, Model model) {
    if (userService.isEnteredUserNotNull(user)) {
      boolean ifSaved = userService.saveUserIfNameNotInTheRepository(user);
      if (ifSaved) {
        return "redirect:/";
      } else {
        model.addAttribute("errorDto", userService
                .sendError("The username already exists"));
        return "enter";
      }
    } else {
      model.addAttribute("errorDto", userService
              .sendError("The username field is empty"));
      return "enter";
    }
  }
}
