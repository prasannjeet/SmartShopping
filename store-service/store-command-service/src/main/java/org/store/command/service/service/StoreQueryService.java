package org.store.command.service.service;

import org.store.domain.service.model.Store;
import org.store.domain.service.repository.StoreRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public class StoreQueryService {

    private StoreRepository storeRepository;

    public StoreQueryService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store findByUserId(String userId) {
        return Optional
                .of(this.storeRepository.findByUserId(userId))
                .orElseThrow(() -> new NoSuchElementException("No store with userId = " + userId));
    }

    public boolean isDuplicate(String userId) {
        return this.storeRepository.isDuplicate(userId);
    }
}
