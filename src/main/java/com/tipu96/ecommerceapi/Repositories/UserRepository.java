package com.tipu96.ecommerceapi.Repositories;

import com.tipu96.ecommerceapi.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmailAndPassword(String email, String password);
}
