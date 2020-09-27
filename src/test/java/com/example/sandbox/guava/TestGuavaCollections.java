package com.example.sandbox.guava;

import com.example.sandbox.model.Person;
import com.google.common.collect.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import org.testng.internal.collections.Ints;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class TestGuavaCollections {

    private final Person person1 = new Person(1, "John", "Ivanov", 20, new Date());

    private final Person person2 = new Person(2, "Roman", "Smith", 16, new Date());

    private final Person person3 = new Person(3, "James", "Brown", 25, new Date());


    @Test
    public void testHashMultiset() {
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

    }

    @Test
    public void testLinkedHashMultiset() {
        Multiset<String> stringLinkedMultiSet = LinkedHashMultiset.create();
        stringLinkedMultiSet.add("test");
        stringLinkedMultiSet.add("test1");
        stringLinkedMultiSet.add("test2");
        stringLinkedMultiSet.add("test2");
        stringLinkedMultiSet.add("test");

        assertThat(stringLinkedMultiSet).contains("test2", "test", "test1");
        assertThat(stringLinkedMultiSet.count("test2")).isEqualTo(2);
        assertThat(stringLinkedMultiSet.elementSet()).containsSequence("test", "test1", "test2");
        assertThat(stringLinkedMultiSet.size()).isEqualTo(5);

    }

    @Test
    public void testTreeMultiset() {
        Multiset<Person> personMultiset = TreeMultiset.create();
        personMultiset.add(person1);
        personMultiset.add(person2);
        personMultiset.add(person3);

        assertThat(personMultiset).containsSequence(person3, person1, person2);
    }

    @Test
    public void testArrayListMultimap() {
        Multimap<String, Person> personArrayMultiMap = ArrayListMultimap.create();

        personArrayMultiMap.put("first", person1);
        personArrayMultiMap.put("second", person2);
        personArrayMultiMap.put("third", person3);

        assertThat(personArrayMultiMap.get("first")).containsExactly(person1);


    }

    @Test
    public void testPersonHashMultimap() {
        Multimap<String, Person> personHashMultimap = HashMultimap.create();
        personHashMultimap.put("first", person1);
        personHashMultimap.put("second", person2);
        personHashMultimap.put("third", person3);

        assertThat(personHashMultimap.keySet()).containsSequence("second", "third", "first");

    }

    @Test
    public void testLinkedListMultimap() {
        Multimap<String, Person> personLinkedList = LinkedListMultimap.create();
        personLinkedList.put("first person", person1);
        personLinkedList.put("second person", person2);
        personLinkedList.put("third person", person3);

        assertThat(personLinkedList.values()).containsSequence(person1, person2, person3);

    }

    @Test
    public void testHashBimap() {
        HashBiMap<String, Person> hashBimap = HashBiMap.create();
        hashBimap.put("first", person1);
        hashBimap.put("second", person2);
        hashBimap.put("third", person3);

        assertThat(hashBimap.inverse().get(person1)).isEqualTo("first");
        assertThat(hashBimap.get("first")).isEqualByComparingTo(person1);

    }

    @Test
    public void testGuavaTreeBasedTable() {
        Table<Integer, Person, String> treeBasedTable = TreeBasedTable.create();
        treeBasedTable.put(1, person1, "first");
        treeBasedTable.put(2, person2, "second");
        treeBasedTable.put(3, person3, "third");

        assertThat(treeBasedTable.row(1)).containsEntry(person1, "first");
        assertThat(treeBasedTable.column(person1)).containsEntry(1, "first");


    }

    @Test
    public void testGuavaHashBasedTable() {
        Table<Integer, Person, String> hashBasedTable = HashBasedTable.create();
        hashBasedTable.put(1, person1, "first");
        hashBasedTable.put(2, person2, "second");
        hashBasedTable.put(3, person3, "third");

        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(2, "second");
        assertThat(hashBasedTable.columnMap()).containsEntry(person2, linkedHashMap);
    }

    @Test
    public void testMutableClassToInstanceMap() {
        ClassToInstanceMap<Object> objectMap = MutableClassToInstanceMap.create();

        objectMap.putInstance(Integer.class, 1);
        objectMap.putInstance(Person.class, person1);

        assertThat(objectMap.getInstance(Integer.class)).isEqualTo(1);
        assertThat(objectMap.getInstance(Person.class)).isEqualTo(person1);
        assertThat(objectMap).containsEntry(Person.class, person1);

    }

    @Test
    public void testTreeRangeSet() {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10));
        rangeSet.add(Range.closedOpen(11, 15));
        rangeSet.add(Range.closedOpen(15, 20));

        assertThat(rangeSet.asRanges()).hasSize(2);
        assertThat(rangeSet.contains(5)).isTrue();
    }

    @Test
    public void testTreeRangeMap() {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo");
        rangeMap.put(Range.open(3, 6), "bar");
        rangeMap.put(Range.open(10, 20), "foo");

        assertThat(rangeMap.get(11)).isNotNull();
        assertThat(rangeMap.span())
                .accepts(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19)
                .rejects(20);
    }

    @Test
    public void testImmutableCollection() {
        ImmutableList<Person> immutableList = ImmutableList
                .<Person>builder()
                .add(person1, person2, person3)
                .build();

        List<Person> personList = immutableList.asList();
        assertThat(personList).isEqualTo(immutableList);

        personList = ImmutableList.copyOf(immutableList);
        assertThat(personList).isEqualTo(immutableList);

    }

    @Test
    public void testGuavaUtility() {

        List<Person> personList = Lists.newArrayList(person1, person2, person3);
        System.out.println(Collections2.filter(personList, person -> person.getAge() == 16 &&
                person.getFirstName().equals("Roman")));

        Collection<Person> filterPerson = Collections2.filter(personList, person -> person.getAge() == 16 &&
                person.getFirstName().equals("Roman"));

        assertThat(filterPerson.size()).isEqualTo(1);

        Collection<String> transformPerson = Collections2
                .transform(personList, person -> person.getFirstName() + "test");

        assertThat(transformPerson).containsExactly("Johntest", "Romantest", "Jamestest");

    }

    @Test
    public void testIterables() {
        List<Person> personList = Lists.newArrayList(person1, person2, person3);

        Iterable<Integer> concatenated = Iterables.concat(
                Ints.asList(1, 2, 3),
                Ints.asList(4, 5, 6));

        assertThat(concatenated.iterator().next()).isEqualTo(1);
        assertThat(Iterables.getLast(personList)).isEqualByComparingTo(person3);
    }
}
