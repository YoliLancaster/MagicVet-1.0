package com.magicvet.domain.service;

import com.magicvet.domain.model.Owner;
import com.magicvet.domain.model.Pet;
import com.magicvet.infrastructure.persistence.OwnerRepository;
import com.magicvet.infrastructure.persistence.PetRepository;

public class AuthServiceImpl implements AuthService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;

    public AuthServiceImpl(OwnerRepository ownerRepository, PetRepository petRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
    }

    @Override
    public boolean authenticate(String email, String password) {
        return ownerRepository.authenticate(email, password);
    }

    @Override
    public int registerOwner(String email, String password, String name, String phone) {
        Owner owner = new Owner(email, password, name, phone);
        return ownerRepository.createOwner(owner);
    }

    @Override
    public void createPetForOwner(int ownerId, Pet pet) {
        petRepository.createPet(pet);
    }

    @Override
    public int getOwnerIdByEmail(String email) {
        return ownerRepository.getOwnerIdByEmail(email);
    }
}
