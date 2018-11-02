package org.store.query.service.service;

import org.store.domain.service.dao.PriceTagDao;
import org.store.domain.service.model.Store;
import org.store.domain.service.model.PriceTag;
import org.store.domain.service.repository.PriceTagRepository;
import org.store.domain.service.repository.StoreRepository;

import java.util.LinkedList;
import java.util.List;

public class StoreQueryService {

    private StoreRepository storeRepository;
    private PriceTagRepository priceTagRepository;

    public StoreQueryService(StoreRepository storeRepository, PriceTagRepository priceTagRepository) {
        this.storeRepository = storeRepository;
        this.priceTagRepository = priceTagRepository;
    }

    public List<PriceTagDao> findAllPriceTags() {
        List<PriceTagDao> priceTagDaos = new LinkedList<>();
        List<PriceTag> priceTags = this.priceTagRepository.findAll();
        priceTags.forEach(priceTag ->
        	priceTagDaos.add(new PriceTagDao(priceTag)));
        return priceTagDaos;
    }
    
    public void saveStoreInfo(Store store) {
        this.storeRepository.save(store);
    }

    public void savePriceTag(PriceTag priceTag) {
        this.priceTagRepository.save(priceTag);
    }

    public void deletePriceTag(String id) {
        this.priceTagRepository.delete(id);
    }

    public void deleteAllPriceTags() {
        this.priceTagRepository.deleteAll();
    }

    public PriceTagDao findPriceTagByBarcode(String barcode) {
        PriceTag priceTag = this.priceTagRepository.findByBarcode(barcode);
        return new PriceTagDao(priceTag);
    }
    
    public Store findStoreInfo(){
    	return this.storeRepository.findAll().get(0);
    }
}
