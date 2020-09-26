package com.example.sandbox.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("age", age)
                .add("birthDate", birthDate)
                .toString();
    }


}
