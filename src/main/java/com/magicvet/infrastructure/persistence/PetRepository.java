package com.magicvet.infrastructure.persistence;

import com.magicvet.domain.model.Pet;

import java.util.List;

public interface PetRepository {
    boolean createPet(Pet pet);
    List<Pet> getPetsByOwnerId(int ownerId);
}
