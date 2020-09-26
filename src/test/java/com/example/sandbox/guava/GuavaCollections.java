package com.example.sandbox.guava;

import com.example.sandbox.model.Person;
import com.google.common.collect.*;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GuavaCollections {

    private final Person person1 = new Person(1, "John", "Ivanov", 20, new Date());

    private final Person person2 = new Person(2, "Roman", "Smith", 16, new Date());

    private final Person person3 = new Person(3, "James", "Brown", 25, new Date());


    @Test
    public void multiSet() {
        Multiset<String> stringMultiset = HashMultiset.create();
        stringMultiset.add("test");
        stringMultiset.add("test");
        stringMultiset.add("test1");
        stringMultiset.add("test2");
        stringMultiset.add("test1");

        assertThat(stringMultiset.count("test")).isEqualTo(2);

        stringMultiset.remove("test");

        assertThat(stringMultiset.count("test")).isEqualTo(1);

        stringMultiset.setCount("test", 25);
        assertThat(stringMultiset.count("test")).isEqualTo(25);

        Multiset<String> stringLinkedMultiSet = LinkedHashMultiset.create();
        stringLinkedMultiSet.add("test");
        stringLinkedMultiSet.add("test1");
        stringLinkedMultiSet.add("test2");
        stringLinkedMultiSet.add("test2");
        stringLinkedMultiSet.add("test");
        System.out.println(stringMultiset.entrySet());
        System.out.println(stringLinkedMultiSet.entrySet());

        Multiset<Person> personMultiset = TreeMultiset.create();
        personMultiset.add(person1);
        personMultiset.add(person2);
        personMultiset.add(person3);
        System.out.println(personMultiset.entrySet());
    }

    @Test
    public void multiMap() {
        Multimap<String, Person> personArrayMultiMap = ArrayListMultimap.create();

        personArrayMultiMap.put("first person", person1);
        personArrayMultiMap.put("second person", person2);
        personArrayMultiMap.put("third person", person3);

        System.out.println(personArrayMultiMap.keySet());
        System.out.println(personArrayMultiMap.entries());
        System.out.println(personArrayMultiMap.asMap().get("first person"));

        Multimap<String, Person> personHashMultimap = HashMultimap.create();
        personHashMultimap.put("first person", person1);
        personHashMultimap.put("second person", person2);
        personHashMultimap.put("third person", person3);

        System.out.println(personHashMultimap.keySet());
        System.out.println(personHashMultimap.entries());
        System.out.println(personHashMultimap.asMap().keySet());
        System.out.println(personHashMultimap.asMap().entrySet());

        Multimap<String, Person> personLinkedList = LinkedListMultimap.create();
        personLinkedList.put("first person", person1);
        personLinkedList.put("second person", person2);
        personLinkedList.put("third person", person3);
        personLinkedList.put("third person", person1);

        System.out.println(personLinkedList.entries());

        Multimap<String, Person> linkedHashMultiMap = LinkedHashMultimap.create();

        linkedHashMultiMap.put("first person", person1);
        linkedHashMultiMap.put("second person", person2);
        linkedHashMultiMap.put("third person", person3);
        linkedHashMultiMap.put("third person", person1);

        System.out.println(linkedHashMultiMap.entries());


    }

    @Test
    public void biMap() {
        HashBiMap<String, Person> hashBiMap = HashBiMap.create();

        hashBiMap.put("first person", person1);
        hashBiMap.put("second person", person2);
        hashBiMap.put("third person", person3);

        String test = hashBiMap.inverse().get(person1);

        Person personFind = hashBiMap.get("first person");
        System.out.println(test + " " + personFind);


    }

    @Test
    public void testGuavaTable() {
        Table<Person, String, String> treeBasedTable = TreeBasedTable.create();
        treeBasedTable.put(person1, "person 1", "1");
        treeBasedTable.put(person2, "person 2", "2");
        treeBasedTable.put(person3, "person 3", "3");

        System.out.println(treeBasedTable.rowKeySet());
        System.out.println(treeBasedTable.cellSet());
        System.out.println(treeBasedTable.columnKeySet());
        System.out.println(treeBasedTable.columnMap());
        System.out.println(treeBasedTable.rowMap());

        Table<Person, String, String> hashBasedTable = HashBasedTable.create();
        hashBasedTable.put(person1, "person 1", "1");
        hashBasedTable.put(person2, "person 2", "2");
        hashBasedTable.put(person3, "person 3", "3");

        System.out.println(hashBasedTable.columnMap());


    }

    @Test
    public void testClassToInstanceMap() {
        ClassToInstanceMap<Object> objectMap = MutableClassToInstanceMap.create();

        objectMap.putInstance(Integer.class, 1);
        objectMap.putInstance(Person.class, person1);

        System.out.println(objectMap.get(Person.class));


    }

    @Test
    public void testRangeSet() {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1, 10]}
        rangeSet.add(Range.closedOpen(11, 15)); // disconnected range: {[1, 10], [11, 15)}
        rangeSet.add(Range.closedOpen(15, 20)); // connected range; {[1, 10], [11, 20)}
        rangeSet.add(Range.openClosed(0, 0)); // empty range; {[1, 10], [11, 20)}
        rangeSet.remove(Range.open(5, 10)); // splits [1, 10]; {[1, 5], [10, 10], [11, 20)}

        System.out.println(rangeSet.asDescendingSetOfRanges());
    }

    @Test
    public void testRangeMap() {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo"); // {[1, 10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo"}
        rangeMap.put(Range.open(10, 20), "foo"); // {[1, 3] => "foo", (3, 6) => "bar", [6, 10] => "foo", (10, 20) => "foo"}
        rangeMap.remove(Range.closed(5, 11)); // {[1, 3] => "foo", (3, 5) => "bar", (11, 20) => "foo"}

        System.out.println(rangeMap.asMapOfRanges());
    }

    @Test
    public void testImmutableCollection() {
        ImmutableList<Person> immutableList = ImmutableList.<Person>builder().add(person1, person2, person3).build();
        System.out.println(immutableList);

        List<Person> personList = immutableList.asList();

        System.out.println(personList);
        personList = ImmutableList.copyOf(immutableList);

        System.out.println(personList);
    }

    @Test
    public void testGuavaUtility() {

        List<Person> personList = Lists.newArrayList(person1, person2, person3);
        System.out.println(Collections2.filter(personList, person -> person.getAge() == 16 &&
                person.getFirstName().equals("Roman")));

        System.out.println(Collections2.transform(personList, person -> person.getFirstName() + "test"));

        System.out.println(Collections2.orderedPermutations(personList, Ordering.natural()));


    }


}
