package org.cart.command.service.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.cart.command.service.aggregate.ProductAggregate;
import org.cart.command.service.command.AddProductCommand;
import org.cart.command.service.command.DeleteProductCommand;
import org.cart.command.service.command.ProductCommand;
import org.cart.command.service.command.UpdateProductQuantityCommand;
import org.cart.domain.service.model.Product;

import java.util.concurrent.CompletableFuture;

public class CommandService {

    private AggregateRepository<ProductAggregate, ProductCommand> aggregateRepository;
    private QueryService queryService;

    public CommandService(AggregateRepository<ProductAggregate, ProductCommand> aggregateRepository,
                          QueryService queryService) {
        this.aggregateRepository = aggregateRepository;
        this.queryService = queryService;
    }

    public CompletableFuture<EntityWithIdAndVersion<ProductAggregate>> addProduct(Product product) throws Exception {
        if (this.queryService.isDuplicateProduct(product)) {
            throw new Exception("Cart already has a product with barcode = " + product.getBarcode());
        }
        return this.aggregateRepository.save(new AddProductCommand(product));
    }

    public CompletableFuture<EntityWithIdAndVersion<ProductAggregate>> updateProductQuantity(String barcode, String userId,
                                                                                             String quantity) throws Exception {
        Product product = this.queryService.findProductByBarcodeAndUserId(barcode, userId);
        product.setQuantity(quantity);
        return this.aggregateRepository
                .update(product.getBarcode(), new UpdateProductQuantityCommand(product.getBarcode(), product));
    }

    public CompletableFuture<EntityWithIdAndVersion<ProductAggregate>> deleteProduct(String barcode, String userId) {
        Product product = this.queryService.findProductByBarcodeAndUserId(barcode, userId);
        return this.aggregateRepository.update(product.getBarcode(), new DeleteProductCommand());
    }
}
