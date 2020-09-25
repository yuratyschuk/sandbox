package com.example.sandbox.assertj;

import com.example.sandbox.model.People;
import org.assertj.core.api.Condition;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.*;

public class PeopleTest {

    private List<People> peopleList;

    private People people1;

    private People people2;

    private People people3;

    Condition<People> peopleCondition = new Condition<People>() {
        @Override
        public boolean matches(People people) {
            return people.getAge() > 15 && people.getAge() < 24;
        }
    };


    @BeforeEach
    public void setup() {
        peopleList = new ArrayList<>();
        people1 = new People(1, "John", "Ivanov", 20, new Date());

        people2 = new People(2, "Roman", "Smith", 16,
                new Date());

        people3 = new People(3, "James", "Brown", 25, new Date());


        peopleList.add(people1);
        peopleList.add(people2);
        peopleList.add(people3);
    }


    @Test
    public void testListIsNotNullAndContainsPeople() {
        peopleList.remove(people3);

        assertThat(peopleList).isNotNull()
                .contains(people1, people2)
                .doesNotContain(people3);
    }

    @Test
    public void testListWithPeopleSize() {
        assertThat(peopleList.size()).isEqualTo(3);
        assertThat(peopleList.size()).isGreaterThan(1);
        assertThat(peopleList.size()).isOdd();
        assertThat(peopleList.size()).isGreaterThanOrEqualTo(3);
    }

    @Test
    public void testListPeopleIn() {
        assertThat(people1).isIn(peopleList);

        peopleList.remove(1);

        assertThat(people2).isNotIn(peopleList);

    }

    @Test
    public void testFindPeopleInList() {
        assertThat(peopleList)
                .extracting(People::getFirstName)
                .anySatisfy(value -> assertThat(value).matches("John"));
    }

    @Test
    public void testFirstPeopleAgeAndPrint() {
        assertThat(people1.getAge()).as("check %s's age", people1.getFirstName())
                .isEqualTo(20);
    }

    @Test
    public void testFilterPeopleByName() {
        assertThat(peopleList).filteredOn(people -> people.getFirstName().contains("John"))
                .containsOnly(people1);
    }

    @Test
    public void testFilterPeopleByAge() {
        assertThat(peopleList).filteredOn(peopleCondition)
                .contains(people1, people2);
    }

    @Test
    public void testExtractFirstNamesAndCheck() {
        assertThat(peopleList).extracting(People::getFirstName)
                .contains("John", "Roman", "James")
                .doesNotContain("Not contain");
    }

    @Test
    public void testExtractFirstNameAndAgeToTupleAndCheck() {
        assertThat(peopleList).extracting(People::getFirstName, People::getAge)
                .contains(tuple("John", 20),
                        tuple("Roman", 16),
                        tuple("James", 25));
    }



}
