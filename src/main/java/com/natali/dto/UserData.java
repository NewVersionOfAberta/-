package com.natali.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserData {
    private int id;
    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;

    public UserData() {
    }

    public UserData(int id, String name, String surname, String email, String login, String password) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }
}
