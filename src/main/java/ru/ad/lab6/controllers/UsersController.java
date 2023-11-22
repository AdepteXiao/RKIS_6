package ru.ad.lab6.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ad.lab6.models.MyUser;
import ru.ad.lab6.services.MyUserService;
import ru.ad.lab6.util.MyUserValidator;

@Controller
@RequestMapping("/users")
public class UsersController {

  private final MyUserValidator validator;

  private final MyUserService service;

  @Autowired
  public UsersController(MyUserValidator validator, MyUserService service) {
    this.validator = validator;
    this.service = service;
  }


  @GetMapping("/login")
  public String loginPage() {
    return "users/login";
  }


  @GetMapping("/register")
  public String regPage(@ModelAttribute("user") MyUser user) {
    return "users/registration";
  }


  @PostMapping("/process_reg")
  public String doReg(@ModelAttribute("user") @Valid MyUser user, BindingResult bindingResult) {
    validator.validate(user, bindingResult);
    if (bindingResult.hasErrors()) {
      return "users/registration";
    } else {
      service.registerUser(user);
      return "redirect:/users/login";
    }
  }

}
