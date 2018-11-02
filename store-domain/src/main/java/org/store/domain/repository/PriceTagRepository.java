package org.store.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.store.domain.model.PriceTag;

@Repository
public interface PriceTagRepository extends JpaRepository<PriceTag, String> {

    PriceTag findByBarcode(String barcode);
}
