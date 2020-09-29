package com.example.sandbox.xml;

import com.example.sandbox.model.Office;
import com.example.sandbox.model.Student;
import com.example.sandbox.xml.util.XmlHandlerForOffice;
import com.example.sandbox.xml.util.XmlHandlerForStudent;
import lombok.SneakyThrows;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestSaxParser {

    private final SAXParserFactory factory = SAXParserFactory.newInstance();

    private final SAXParser parser = factory.newSAXParser();


    private final Student student1 = new Student(1, "Yurii", "Tyshchuk", 83);
    private final Student student2 = new Student(2, "John", "Ivanov", 73);
    private final Student student3 = new Student(3, "Roman", "Smith", 99);
    private final Student student4 = new Student(4, "James", "Brown", 70);


    public TestSaxParser() throws ParserConfigurationException, SAXException {
    }

    @SneakyThrows
    @Test
    public void testSaxParserStudent() {
        XmlHandlerForStudent handler = new XmlHandlerForStudent();
        parser.parse(TestSaxParser.class.getResourceAsStream("/file_for_parse.xml"), handler);

        List<Student> students = handler.getStudentList();

        assertThat(students).containsExactly(student1, student2, student3, student4);
        assertThat(students.size()).isEqualTo(4);
    }

    @SneakyThrows
    @Test
    public void testSaxParserEmployee() {
        XmlHandlerForOffice handler = new XmlHandlerForOffice();

        parser.parse(TestSaxParser.class.getResourceAsStream("/file_for_parse2.xml"), handler);

        List<Office> officeList = handler.getOfficeList();

        assertThat(officeList.size()).isEqualTo(2);

        assertThat(officeList)
                .extracting(Office::getEmployeeList)
                .isNotEmpty();
    }


}