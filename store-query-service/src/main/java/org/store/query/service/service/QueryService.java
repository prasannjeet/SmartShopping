package org.store.query.service.service;

import org.store.domain.dao.StoreDao;
import org.store.domain.model.Store;
import org.store.domain.repository.PriceTagRepository;
import org.store.domain.repository.StoreRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class QueryService {

    private StoreRepository storeRepository;
    private PriceTagRepository priceTagRepository;

    public QueryService(StoreRepository storeRepository, PriceTagRepository priceTagRepository) {
        this.storeRepository = storeRepository;
        this.priceTagRepository = priceTagRepository;
    }

    public List<Store> findAll() {
        return this.storeRepository.findAll();
    }

    public StoreDao findById(String id) {
        return new StoreDao(
                Optional
                        .of(this.storeRepository.findOne(id))
                        .orElseThrow(() -> new NoSuchElementException("No store with id = " + id)),
                this.priceTagRepository.findAll()
        );
    }
}
