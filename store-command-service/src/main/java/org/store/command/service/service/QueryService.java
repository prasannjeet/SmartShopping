package org.store.command.service.service;

import org.store.domain.service.model.Store;
import org.store.domain.service.model.PriceTag;
import org.store.domain.service.repository.StoreRepository;
import org.store.domain.service.repository.PriceTagRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public class QueryService {

    private StoreRepository storeRepository;
    private PriceTagRepository priceTagRepository;

    public QueryService(StoreRepository storeRepository, PriceTagRepository priceTagRepository) {
        this.storeRepository = storeRepository;
        this.priceTagRepository = priceTagRepository;
    }

    public boolean isDuplicatePriceTag(PriceTag priceTag) {
        PriceTag duplicate = this.priceTagRepository.findByBarcode(priceTag.getBarcode());
       	return duplicate != null;
    }


    public PriceTag findPriceTagByBarcode(String barcode) {
        return Optional
                .of(this.priceTagRepository.findByBarcode(barcode))
                .orElseThrow(() -> new NoSuchElementException("No priceTag with barcode = " + barcode));
    }
    
    public Store findStoreInfo(){
    	return this.storeRepository.findAll().get(0);
    }
    
    public Store setStoreInfo(Store store){
    	return storeRepository.save(store);
    }
}
