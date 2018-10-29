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

    public Store findById(String id) {
        return Optional
                .of(this.storeRepository.findById(id))
                .orElseThrow(() -> new NoSuchElementException("No store with id= " + id));
    }
}
