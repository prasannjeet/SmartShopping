package org.store.command.service.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;

import java.util.concurrent.CompletableFuture;

import org.store.command.service.aggregate.PriceTagAggregate;
import org.store.command.service.command.AddPriceTagCommand;
import org.store.command.service.command.DeletePriceTagCommand;
import org.store.command.service.command.PriceTagCommand;
import org.store.command.service.command.UpdatePriceTagCommand;
import org.store.domain.service.model.PriceTag;

public class CommandService {

    private AggregateRepository<PriceTagAggregate, PriceTagCommand> aggregateRepository;
    private QueryService queryService;

    public CommandService(AggregateRepository<PriceTagAggregate, PriceTagCommand> aggregateRepository,
                          QueryService queryService) {
        this.aggregateRepository = aggregateRepository;
        this.queryService = queryService;
    }

    public CompletableFuture<EntityWithIdAndVersion<PriceTagAggregate>> addPriceTag(PriceTag priceTag) throws Exception {
        if (this.queryService.isDuplicatePriceTag(priceTag)) {
            throw new Exception("Cart already has a priceTag with barcode = " + priceTag.getBarcode());
        }
        return this.aggregateRepository.save(new AddPriceTagCommand(priceTag));
    }

    public CompletableFuture<EntityWithIdAndVersion<PriceTagAggregate>> updatePriceTag(String barcode, double price) throws Exception {
        PriceTag priceTag = this.queryService.findPriceTagByBarcode(barcode);
        priceTag.setPrice(price);
        return this.aggregateRepository
                .update(priceTag.getBarcode(), new UpdatePriceTagCommand(priceTag.getBarcode(), priceTag));
    }

    public CompletableFuture<EntityWithIdAndVersion<PriceTagAggregate>> deletePriceTag(String barcode) {
        PriceTag priceTag = this.queryService.findPriceTagByBarcode(barcode);
        return this.aggregateRepository.update(priceTag.getBarcode(), new DeletePriceTagCommand(priceTag));
    }
}
