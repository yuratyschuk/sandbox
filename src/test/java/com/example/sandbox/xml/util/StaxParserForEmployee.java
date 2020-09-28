package com.example.sandbox.xml.util;

import com.example.sandbox.model.Employee;
import lombok.SneakyThrows;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class StaxParserForEmployee {

    private Employee employee;

    private final List<Employee> employeeList = new ArrayList<>();

    private final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();


    @SneakyThrows
    public void parse(String fileName) {

    XMLEventReader xmlEventReader = xmlInputFactory
            .createXMLEventReader(new FileInputStream(fileName));

        while (xmlEventReader.hasNext()) {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();

            if (xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();


                if (startElement.getName().getLocalPart().equals("name")) {
                    employee = new Employee();
                    xmlEvent = xmlEventReader.nextEvent();
                    employee.setName(xmlEvent.asCharacters().getData());
                } else if (startElement.getName().getLocalPart().equals("job")) {
                    xmlEvent = xmlEventReader.nextEvent();

                    employee.setJob(xmlEvent.asCharacters().getData());
                }
            }
            if (xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();
                if (endElement.getName().getLocalPart().equals("employee")) {
                    employeeList.add(employee);
                }
            }
        }
    }

    public List<Employee> getData() {
        return employeeList;
    }
}
