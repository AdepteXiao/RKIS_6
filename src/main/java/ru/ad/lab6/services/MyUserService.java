package ru.ad.lab6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ad.lab6.models.MyUser;
import ru.ad.lab6.repositories.MyUserRepository;

@Service
@Transactional(readOnly = true)
public class MyUserService {

  private final MyUserRepository repository;

  private final PasswordEncoder passwordEncoder;


  @Autowired
  public MyUserService(MyUserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }


  public boolean isUsernameTaken(String username) {
    return repository.findByUsername(username).isPresent();
  }

  @Transactional
  public void registerUser(MyUser user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    if (user.getUsername().equals("Admin")) {
      user.setRole("ROLE_ADMIN");
    } else {
      user.setRole("ROLE_USER");
    }
    repository.save(user);
  }
}
