package org.user.query.service.service;

import org.user.domain.model.User;
import org.user.domain.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class QueryService {

    private UserRepository userRepository;

    public QueryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(String id) {
        return Optional
                .of(this.userRepository.findOne(id))
                .orElseThrow(() -> new NoSuchElementException("No user with id = " + id));
    }
}
