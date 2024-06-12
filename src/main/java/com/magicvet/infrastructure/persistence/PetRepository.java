package com.magicvet.infrastructure.persistence;

import com.magicvet.domain.model.Pet;

public interface PetRepository {
    boolean createPet(Pet pet);

}
