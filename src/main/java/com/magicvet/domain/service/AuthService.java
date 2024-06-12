package com.magicvet.domain.service;

public interface AuthService {
    boolean authenticate(String email, String password);

    void registerOwnerAndPet(String email, String password, String name, String phone,String petName, String petType, int petAge, String petBreed, String petGender);
}
