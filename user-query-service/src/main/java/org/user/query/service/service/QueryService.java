package org.user.query.service.service;

import org.user.domain.service.model.User;
import org.user.domain.service.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class QueryService {

    private UserRepository repository;

    public QueryService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAllUsers() {
        return this.repository.findAll();
    }

    public User findUserById(String id) {
        return Optional
                .of(this.repository.findOne(id))
                .orElseThrow(() -> new NoSuchElementException("No user with id = " + id));
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public void deleteUser(String id) {
        this.repository.delete(id);
    }

    public void deleteAll() {
        this.repository.deleteAll();
    }
}
