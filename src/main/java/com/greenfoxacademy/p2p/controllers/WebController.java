package com.greenfoxacademy.p2p.controllers;

import com.greenfoxacademy.p2p.factories.WebFactory;
import com.greenfoxacademy.p2p.models.DTOs.ErrorMessageDTO;
import com.greenfoxacademy.p2p.models.Message;
import com.greenfoxacademy.p2p.models.User;
import com.greenfoxacademy.p2p.services.MessageService;
import com.greenfoxacademy.p2p.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WebController {

  @Autowired
  UserService userService;
  @Autowired
  MessageService messageService;
  @Autowired
  WebFactory webFactory;

  @GetMapping("/")
  public String loadMainPage(@ModelAttribute("errorDto") ErrorMessageDTO errorMessageDTO,
                                   Model model) {
    if (userService.ifUserExist()) {
      model.addAttribute("user", userService.findUserById((long)1));
      if (errorMessageDTO.getErrorMessage() == null) {
        model.addAttribute("errorDto", userService.sendError(""));
      } else {
        model.addAttribute("errorDto", errorMessageDTO);
      }
      model.addAttribute("newmessage", webFactory.createNewMessage());
      if (messageService.ifMessageExist()) {
        model.addAttribute("messages", messageService.getAllMessages());
      } else {
        model.addAttribute("messages", messageService.createNewMessageList());
      }
      return "main";
    } else {
      return "redirect:/enter";
    }

  }

  @PostMapping("/update")
  public String updateUserName(@ModelAttribute(value = "user") User user,
                                     final RedirectAttributes redirectAttributes) {

    if (userService.isEnteredUserNotNull(user)) {
      userService.updateUser(user);
      return "redirect:/";
    } else {
      ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
      errorMessageDTO.setErrorMessage("The username field is empty");
      redirectAttributes.addFlashAttribute("errorDto", errorMessageDTO);
      redirectAttributes.addFlashAttribute("user", user);
      return "redirect:/";
    }
  }

  @PostMapping("/submit")
  public String submitNewMessage(@ModelAttribute(value = "newmessage") Message newMessage,
                                 @ModelAttribute(value = "user") User user,
                                 Model model) {
    if (messageService.isEnteredMessageNull(newMessage)) {
      model.addAttribute("user", user);
      model.addAttribute("errorDto", userService
              .sendError("The message field is empty"));
      if (messageService.ifMessageExist()) {
        model.addAttribute("messages", messageService.getAllMessages());
      } else {
        model.addAttribute("messages", messageService.createNewMessageList());
      }
      return "main";
    } else {
      messageService.addMessageToList(newMessage, user);
      return "redirect:/";
    }
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
