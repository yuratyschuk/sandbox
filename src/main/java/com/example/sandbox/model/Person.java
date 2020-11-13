package com.example.sandbox.model;

import com.google.common.collect.ComparisonChain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Comparable<Person> {

    private int id;

    private String firstName;

    private String lastName;

    private int age;

    private Date birthDate;

    @Override
    public int compareTo(Person object) {
        return ComparisonChain.start()
                .compare(this.firstName, object.firstName)
                .compare(this.lastName, object.lastName)
                .compare(this.age, object.age)
                .result();
    }

}
