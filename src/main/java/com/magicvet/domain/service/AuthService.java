package com.magicvet.domain.service;

import com.magicvet.domain.model.Pet;

public interface AuthService {
    boolean authenticate(String email, String password);

    int registerOwner(String email, String password, String name, String phone);

    void createPetForOwner(int ownerId, Pet pet);

    int getOwnerIdByEmail(String email);
}
