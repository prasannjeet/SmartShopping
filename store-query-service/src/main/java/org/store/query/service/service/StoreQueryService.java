package org.store.query.service.service;

import org.cart.domain.service.dao.CartDaoForStore;
import org.cart.domain.service.dao.ProductDaoForStore;
import org.store.domain.service.dao.PriceList;
import org.store.domain.service.dao.PriceTagDao;
import org.store.domain.service.model.Store;
import org.store.domain.service.model.PriceTag;
import org.store.domain.service.repository.PriceTagRepository;
import org.store.domain.service.repository.StoreRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StoreQueryService {

    private static final double MAX_RANGE = 10;
	private StoreRepository storeRepository;
    private PriceTagRepository priceTagRepository;
    private MapService mapService;

    public StoreQueryService(StoreRepository storeRepository, PriceTagRepository priceTagRepository, MapService mapService) {
        this.storeRepository = storeRepository;
        this.priceTagRepository = priceTagRepository;
        this.mapService = mapService;
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

	public PriceList getPriceList(CartDaoForStore cart) {
		Store storeInfo = findStoreInfo();
		double storeDistance = mapService.GetDistance(cart.getUserLocation(), storeInfo.getLocation());
		
		if (storeDistance > MAX_RANGE)
			return null;
		
		List<PriceTagDao> priceTags = new ArrayList<PriceTagDao>();
		for (ProductDaoForStore product : cart.getProducts()){
			priceTags.add(findPriceTagByBarcode(product.getBarcode()));
		}
		
		return new PriceList(cart.getUserId(), storeInfo.getName(), storeDistance, priceTags);		
	}
}
