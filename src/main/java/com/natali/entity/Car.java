package com.natali.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car extends Entity {
    private int id;
    private int carId;
    private String mark;
    private boolean isRented;
    //private Currency price;
}
