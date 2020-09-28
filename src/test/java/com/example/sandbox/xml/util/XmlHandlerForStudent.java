package com.example.sandbox.xml.util;

import com.example.sandbox.model.Person;
import com.example.sandbox.model.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XmlHandlerForStudent extends DefaultHandler {


    private final ArrayList<Student> studentsList = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("student")) {
            int id = Integer.parseInt(attributes.getValue("id"));
            String firstName = attributes.getValue("firstName");
            String lastName = attributes.getValue("lastName");
            int mark = Integer.parseInt(attributes.getValue("mark"));

            studentsList.add(new Student(id, firstName, lastName, mark));
        }
    }

    public List<Student> getStudentList() {
        return studentsList;
    }
}
