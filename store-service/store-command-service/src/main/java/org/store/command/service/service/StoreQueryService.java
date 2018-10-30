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

    public Store findByStoreName(String storeName) {
        return Optional
                .of(this.storeRepository.findByStoreName(storeName))
                .orElseThrow(() -> new NoSuchElementException("No store with storeName = " + storeName));
    }

    public boolean isDuplicate(String storeName) {
        return this.storeRepository.isDuplicate(storeName);
    }
}
