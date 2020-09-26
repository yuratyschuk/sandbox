package com.example.sandbox.guava;

import com.example.sandbox.model.Person;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GuavaOrdering {


    private final Person person1 = new Person(1, "John", "Ivanov", 20, new Date());

    private final Person person2 = new Person(2, "Roman", "Smith", 16, new Date());

    private final Person person3 = new Person(3, "James", "Brown", 25, new Date());

    private final List<Person> personList = Lists.newArrayList(person1, person2, person3);

    private final Ordering<Person> ordering = Ordering
            .natural()
            .nullsFirst()
            .onResultOf(person -> {
                assert person != null;
                return person.getAge();
            });

    @Test
    public void testGuavaOrderingPersonList() {
        personList.sort(ordering);

        assertThat(personList).isSortedAccordingTo(ordering);
    }
}
