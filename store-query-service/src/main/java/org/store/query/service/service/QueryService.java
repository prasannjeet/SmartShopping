package org.store.query.service.service;

import org.store.domain.dao.StoreDao;
import org.store.domain.model.Store;
import org.store.domain.repository.PriceTagRepository;
import org.store.domain.repository.StoreRepository;

import java.util.List;

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


    public boolean isIdentified() {
        return this.storeRepository.isIdentified();
    }
    

    public StoreDao findSingleton() {
        return new StoreDao(this.storeRepository.findAll().get(0),this.priceTagRepository.findAll());
    }
}
