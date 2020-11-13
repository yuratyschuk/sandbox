package com.example.sandbox.reflection;

import com.example.sandbox.model.Person;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReflectionExampleTest {

    private static final String PERSON_FIELD_NAME = "firstName";

    private static final String PERSON_NEW_NAME = "New name";

    private final Person person = new Person(4, "Mark", "Johns", 25, new Date());

    private final PersonService personService = new PersonService();

    @Before
    public void setup() {
        Class<?> personClass = personService.getClass();

        Method[] methods = personClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Access.class)) {
                Access access = method.getAnnotation(Access.class);

                if (!access.value()) {
                    method.setAccessible(false);
                }
            }
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAccessToPersonServiceMethodsWillThrowException() {
        personService.save(person);
        personService.changeFirstName(PERSON_NEW_NAME, 4);
    }

    @Test
    public void testAccessToPersonServiceDeleteWillNotAccess() {
        List<Person> personList = personService.getAll();

        personService.deleteById(2);

        List<Person> personListAfterDelete = personService.getAll();

        assertEquals(personList.size(), personListAfterDelete.size());
    }

    @Test
    public void changePrivateVariableData() throws NoSuchFieldException, IllegalAccessException {
        Class<?> personClass = person.getClass();

        Field personName = personClass.getDeclaredField(PERSON_FIELD_NAME);

        personName.setAccessible(true);
        personName.set(person, PERSON_NEW_NAME);

        assertEquals(PERSON_NEW_NAME, person.getFirstName());
    }

    @Test
    @SneakyThrows
    public void setNewInstanceAndChangePrivateVariableData() {
        Class<?> personClass = Person.class;

        Object person = personClass.newInstance();

        Field field = person.getClass().getDeclaredField(PERSON_FIELD_NAME);

        field.setAccessible(true);
        field.set(person, PERSON_NEW_NAME);

        assertEquals(PERSON_NEW_NAME, field.get(person));
    }

    @Test(expected = IllegalAccessException.class)
    @SneakyThrows
    public void setNewInstanceAndChangePrivateVariableDataWithoutChangingAccessModifier() {
        Class<?> personClass = Person.class;

        Object person = personClass.newInstance();

        Field field = person.getClass().getDeclaredField(PERSON_FIELD_NAME);

        field.set(person, PERSON_NEW_NAME);

        assertEquals(PERSON_NEW_NAME, field.get(person));
    }
}
