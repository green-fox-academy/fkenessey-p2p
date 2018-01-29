package com.greenfoxacademy.p2p.controllers;

import com.greenfoxacademy.p2p.factories.WebFactory;
import com.greenfoxacademy.p2p.models.User;
import com.greenfoxacademy.p2p.repositories.UserRepository;
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

  @GetMapping("/")
  public String loadMainPage() {
    return "main";
  }

  @GetMapping("/enter")
  public String enterUser(Model model) {
    model.addAttribute("user", userService.createNewUser());
    model.addAttribute("errorDto", userService.sendError(""));
    return "enter";
  }

  @PostMapping("/enter")
  public String addUser(@ModelAttribute(value = "user") User user, Model model) {
    boolean ifSaved = userService.saveUserIfNameNotInTheRepository(user);
    if (userService.isEnteredUserNotNull(user)) {
      if (ifSaved) {
        return "redirect:/";
      } else {
        model.addAttribute("errorDto", userService
                .sendError("The username already exists"));
        return "/enter";
      }
    } else {
      model.addAttribute("errorDto", userService
              .sendError("The username field is empty"));
      return "/enter";
    }



  }
}
