package com.magicvet.domain.service;

import com.magicvet.domain.model.Pet;
import com.magicvet.infrastructure.database.DBService;
import com.magicvet.infrastructure.persistence.PetRepository;

import java.util.List;

public class PetRepositoryImpl  implements PetRepository {
    private final DBService dbService;

    public PetRepositoryImpl(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public boolean createPet(Pet pet) {
        return dbService.addPetToDB(pet);
    }

    @Override
    public List<Pet> getPetsByOwnerId(int ownerId) {
        return dbService.getPetsByOwnerId(ownerId);
    }
}
