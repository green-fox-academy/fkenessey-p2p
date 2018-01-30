package com.greenfoxacademy.p2p.repositories;

import com.greenfoxacademy.p2p.models.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
}
