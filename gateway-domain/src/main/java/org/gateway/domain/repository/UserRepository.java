package org.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.user.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    default void shouldNotBeDuplicate(User user) throws Exception {
        if (this.findByUsername(user.getUsername()) != null) {
            throw new Exception("Duplicate user with username = " + user.getUsername());
        }
    }
}
