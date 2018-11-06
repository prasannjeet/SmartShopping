package org.store.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.store.domain.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {

    default boolean isIdentified() {
        return !this.findAll().isEmpty();
    }

}
