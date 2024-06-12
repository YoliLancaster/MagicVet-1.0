package com.magicvet.domain.model;

public class Pet {
    private int ownerId;
    private String name;
    private String type;
    private int age;
    private String breed;
    private String gender;

    public Pet(int ownerId, String name, String type, int age, String breed, String gender) {
        this.ownerId = ownerId;
        this.name = name;
        this.type = type;
        this.age = age;
        this.breed = breed;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
