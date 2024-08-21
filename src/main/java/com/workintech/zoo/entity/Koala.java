package com.workintech.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Koala {
    private int id;
    private String name;
    private double weight;
    private double sleepHour;
    private String gender;
}
