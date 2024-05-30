package com.magicvet.service.jdbc.impl;

import com.magicvet.model.Owner;
import com.magicvet.service.jdbc.StorageClient;

import java.util.List;

public class JdbcClient implements StorageClient {
    @Override
    public void saveOwner(Owner owner) {

    }

    @Override
    public void savePet(Pet pet) {

    }

    @Override
    public List<Owner> findAllClients() {
        return List.of();
    }
}
