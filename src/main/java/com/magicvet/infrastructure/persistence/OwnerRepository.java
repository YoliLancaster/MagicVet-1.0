package com.magicvet.infrastructure.persistence;


import com.magicvet.domain.model.Owner;

public interface OwnerRepository {
    // void createUser(Authentication.User user);//createOwner
    int createOwner(Owner owner);

    boolean authenticate(String email, String password);
}
