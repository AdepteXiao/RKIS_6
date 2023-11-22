package ru.ad.lab6.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ad.lab6.models.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Integer> {
  Optional<MyUser> findByUsername(String username);



}
