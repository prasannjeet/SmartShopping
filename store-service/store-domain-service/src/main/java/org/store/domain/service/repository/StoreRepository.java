package org.store.domain.service.repository;

import org.store.domain.service.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {

    Store findByUserId(String userId);

    default boolean isDuplicate(String userId) {
        return this.findByUserId(userId) != null;
    }
}
