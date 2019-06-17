package com.licenta.project.repositories.mongo;

import com.licenta.project.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
   User findByEmailAndPassword(String email, String password);

   List<User> findUsersByEmailSchedule(String time);
}
