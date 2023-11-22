package ru.ad.lab6.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ad.lab6.models.MyUser;
import ru.ad.lab6.repositories.MyUserRepository;
import ru.ad.lab6.security.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService {

  private final MyUserRepository repository;

  @Autowired
  public MyUserDetailsService(MyUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<MyUser> user = repository.findByUsername(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }
    return new MyUserDetails(user.get());
  }

}
