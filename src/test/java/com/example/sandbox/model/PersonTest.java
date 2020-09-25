package com.example.sandbox.model;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class PersonTest {


    private final Person person1 = new Person(1, "John", "Ivanov", 20, new Date());

    private final Person person2 = new Person(2, "Roman", "Smith", 16, new Date());

    private final Person person3 = new Person(3, "James", "Brown", 25, new Date());

    private final List<Person> personList = Lists.newArrayList(person1, person2, person3);

    @Test
    public void testListIsNotNullAndContainsPeople() {
        personList.remove(person3);

        assertThat(personList).isNotNull()
                .contains(person1, person2)
                .doesNotContain(person3);
    }

    @Test
    public void testListWithPeopleSize() {
        assertThat(personList.size()).isEqualTo(3);
        assertThat(personList.size()).isGreaterThan(1);
        assertThat(personList.size()).isOdd();
        assertThat(personList.size()).isGreaterThanOrEqualTo(3);
    }

    @Test
    public void testListPeopleIn() {
        assertThat(person1).isIn(personList);

        personList.remove(1);

        assertThat(person2).isNotIn(personList);

    }

    @Test
    public void testFindPeopleInList() {
        assertThat(personList)
                .extracting(Person::getFirstName)
                .anySatisfy(value -> assertThat(value).matches("John"));
    }

    @Test
    public void testFirstPeopleAgeAndPrint() {
        assertThat(person1.getAge()).as("check %s's age", person1.getFirstName())
                .isEqualTo(20);
    }

    @Test
    public void testFilterPeopleByName() {
        assertThat(personList).filteredOn(person -> person.getFirstName().contains("John"))
                .containsOnly(person1);
    }

    @Test
    public void testFilterPeopleByAge() {
        assertThat(personList).filteredOn(person -> person.getAge() > 15 && person.getAge() < 24)
                .contains(person1, person2);
    }

    @Test
    public void testExtractFirstNamesAndCheck() {
        assertThat(personList).extracting(Person::getFirstName)
                .contains("John", "Roman", "James")
                .doesNotContain("Not contain");
    }

    @Test
    public void testExtractFirstNameAndAgeToTupleAndCheck() {
        assertThat(personList).extracting(Person::getFirstName, Person::getAge)
                .contains(tuple("John", 20),
                        tuple("Roman", 16),
                        tuple("James", 25));
    }


}
