package com.natali.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Entity{
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
}
