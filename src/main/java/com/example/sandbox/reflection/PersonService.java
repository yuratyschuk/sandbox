
package com.example.sandbox.reflection;


import com.example.sandbox.model.Person;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

public class PersonService {
    private final Person person1 = new Person(1, "John", "Ivanov", 20, new Date());

    private final Person person2 = new Person(2, "Roman", "Smith", 16, new Date());

    private final Person person3 = new Person(3, "James", "Brown", 25, new Date());

    private final List<Person> personList = Lists.newArrayList(person1, person2, person3);

    @Access
    public Person changeFirstName(String name, int id) {
        personList.get(id).setFirstName(name);

        return personList.get(id);
    }

    @Access(value = false)
    public Person save(Person person) {
        personList.add(person);

        return person;
    }

    @Access(value = false)
    public void deleteById(int id) {
        personList.removeIf(x -> x.getId() == id);
    }

    @Access
    public List<Person> getAll() {
        return personList;
    }
}
