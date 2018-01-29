package com.greenfoxacademy.p2p.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

  @GetMapping("/")
  public String loadMainPage(Model model) {
    return "main";
  }
}
