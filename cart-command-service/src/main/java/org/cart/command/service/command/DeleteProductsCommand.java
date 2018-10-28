package org.cart.command.service.command;

import java.util.List;

public class DeleteProductsCommand implements ProductCommand {

    private List<String> ids;

    public DeleteProductsCommand() {

    }

    public DeleteProductsCommand(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getIds() {
        return this.ids;
    }
}
