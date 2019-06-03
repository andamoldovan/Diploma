package com.licenta.project.repositories;

import com.licenta.project.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
   User findByEmailAndPassword(String email, String password);
}
