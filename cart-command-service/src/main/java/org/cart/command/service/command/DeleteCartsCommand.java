package org.cart.command.service.command;

import java.util.List;

public class DeleteCartsCommand implements CartCommand {

    private List<String> ids;

    public DeleteCartsCommand() {

    }

    public DeleteCartsCommand(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getIds() {
        return this.ids;
    }
}
