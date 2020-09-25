package com.example.sandbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class People {

    private int id;

    private String firstName;

    private String lastName;

    private int age;

    private Date birthDate;
}
