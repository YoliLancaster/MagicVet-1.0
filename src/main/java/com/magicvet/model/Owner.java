package com.magicvet.model;

import lombok.Data;

@Data
public class Owner {
    private String email;
    private String password;
    private String name;
    private int phone;
}
