package org.store.command.service.command;

import java.util.List;

public class DeleteStoresCommand implements StoreCommand {

    private List<String> ids;

    public DeleteStoresCommand() {

    }

    public DeleteStoresCommand(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getIds() {
        return this.ids;
    }
}
