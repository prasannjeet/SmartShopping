package org.store.domain.service.repository;

import org.store.domain.service.model.PriceTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceTagRepository extends JpaRepository<PriceTag, String> {
    PriceTag findByBarcode(String barcode);
}
