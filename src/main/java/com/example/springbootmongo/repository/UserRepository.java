package com.example.springbootmongo.repository;

import com.example.springbootmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
