package org.user.domain.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.user.domain.service.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    default boolean isDuplicate(User user) {
        return this.findByUsername(user.getUsername()) != null;
    }
}
