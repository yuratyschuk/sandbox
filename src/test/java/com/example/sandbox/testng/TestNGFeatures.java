package com.example.sandbox.testng;

import com.example.sandbox.model.Person;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class TestNGFeatures {


    @DataProvider(name = "personTests")
    public Object[][] createData1() {
        return new Object[][] {
                { new Person(1, "John", "Ivanov", 20, new Date())},

                {new Person(2, "Roman", "Smith", 16, new Date())},

                {new Person(3, "James", "Brown", 25, new Date())}
        };
    }

    @Test(dataProvider = "personTests")
    public void testGivenPersonAgeParameters(Person person) {
        assertThat(person.getAge()).isGreaterThan(15);
    }

    @Test(dependsOnMethods = {"testGivenPersonAgeParameters"}, dataProvider = "personTests")
    public void printPersonInfoIfSuccess(Person person) {
        log.info(person);
    }

    @Test(dataProvider = "personTests")
    public void verifyData1(Person person) {
        assertThat(person).isNotNull();
        assertThat(person.getFirstName()).isNotEmpty();
        assertThat(person.getAge()).isGreaterThan(15);
    }

}
