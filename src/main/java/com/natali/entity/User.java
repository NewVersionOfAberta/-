package com.natali.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Entity{
    private int id;
    private String name;
    private String password;
    private Role role;
}
