package com.magicvet.infrastructure.persistence;


import com.magicvet.domain.model.Owner;

import java.util.Optional;

public interface OwnerRepository {
    // void createUser(Authentication.User user);//createOwner
    int createOwner(Owner owner);

    boolean authenticate(String email, String password);

    int getOwnerIdByEmail(String email);

    Optional<Owner> getOwnerWithPets(String email);
}
