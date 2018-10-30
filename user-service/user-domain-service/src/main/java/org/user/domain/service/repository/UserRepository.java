package org.user.domain.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.user.domain.service.model.User;

@Repository
public interface UserRepository extends JpaRepository<User , String>{
	
	User findByUserId(String userId);
	
	
	default boolean isDuplicate (String userId) {
		return this.findByUserId(userId) !=null;
	}

}
