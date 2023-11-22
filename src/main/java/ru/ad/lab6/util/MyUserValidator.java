package ru.ad.lab6.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ad.lab6.models.MyUser;
import ru.ad.lab6.services.MyUserService;

@Component
public class MyUserValidator implements Validator {

  private final MyUserService service;

  @Autowired
  public MyUserValidator(MyUserService service) {
    this.service = service;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return MyUser.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    MyUser user = (MyUser) target;
    if (service.isUsernameTaken(user.getUsername())) {
      errors.rejectValue("username", "", "This username was already taken!");
    }
  }
}
