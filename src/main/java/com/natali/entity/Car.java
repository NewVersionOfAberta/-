package com.natali.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Currency;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Car extends Entity {
    private int id;
    private String mark;
    private boolean isRented;
    private int price;
    private String color;
}
