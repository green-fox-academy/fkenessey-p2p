package com.greenfoxacademy.p2p.repositories;

import com.greenfoxacademy.p2p.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  List<User> findAllByUsername(String usernameToSearch);
}
