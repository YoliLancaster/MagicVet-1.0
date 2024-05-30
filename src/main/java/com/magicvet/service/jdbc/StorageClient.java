package com.magicvet.service.jdbc;

import com.magicvet.model.Owner;

import java.util.List;

public interface StorageClient {
    void saveOwner(Owner owner);

    void savePet(Pet pet);

    List<Owner> findAllClients();
}
