package com.mazzo.nxcrud.repository;

import com.mazzo.nxcrud.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
