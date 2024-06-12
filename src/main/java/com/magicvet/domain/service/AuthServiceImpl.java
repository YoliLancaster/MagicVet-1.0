package com.magicvet.domain.service;

import com.magicvet.domain.model.Owner;
import com.magicvet.domain.model.Pet;
import com.magicvet.infrastructure.persistence.OwnerRepository;
import com.magicvet.infrastructure.persistence.PetRepository;

public class AuthServiceImpl implements AuthService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;

    public AuthServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public boolean authenticate(String email, String password) {
        return ownerRepository.authenticate(email, password);
    }

    @Override
    public void registerOwnerAndPet(String email, String password, String name, String phone, String petName, String petType, int petAge, String petBreed, String petGender) {
        Owner owner = new Owner(email, password, name, phone);
        int ownerId = ownerRepository.createOwner(owner);

        if (ownerId > 0) {
            createPetForOwner(ownerId, petName, petType, petAge, petBreed, petGender);
        } else {
            System.out.println("Registration failed");
        }
    }

    private void createPetForOwner(int ownerId, String petName, String petType, int petAge, String petBreed, String petGender) {
        Pet pet = new Pet(ownerId, petName, petType, petAge, petBreed, petGender);
        boolean isPetRegistered = petRepository.createPet(pet);

        if (isPetRegistered) {
            System.out.println("Registration successful");
        } else {
            System.out.println("Pet registration failed");
        }
    }
}
