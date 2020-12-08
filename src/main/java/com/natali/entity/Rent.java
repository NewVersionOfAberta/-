package com.natali.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rent extends Entity {
    private int id;
    private int ownerId;
    private int carId;
    private Date startRentTime;
    private Date endRentTime;
}
