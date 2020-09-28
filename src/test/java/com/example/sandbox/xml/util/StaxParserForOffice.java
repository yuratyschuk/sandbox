package com.example.sandbox.xml.util;

import com.example.sandbox.model.Employee;
import com.example.sandbox.model.Office;
import lombok.SneakyThrows;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StaxParserForOffice {

    private Employee employee;

    private List<Employee> employeeList = new ArrayList<>();

    private List<Office> officeList = new ArrayList<>();

    private final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    private Office office;

    @SneakyThrows
    public void parse(String fileName) {

    XMLEventReader xmlEventReader = xmlInputFactory
            .createXMLEventReader(new FileInputStream(fileName));

        while (xmlEventReader.hasNext()) {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();

            if (xmlEvent.isStartElement()) {

                StartElement startElement = xmlEvent.asStartElement();
                if(startElement.getName().getLocalPart().equals("office")) {
                    office = new Office();
                    xmlEvent = xmlEventReader.nextEvent();

                   Iterator<Attribute> attributeIterator = startElement.asStartElement().getAttributes();

                   while (attributeIterator.hasNext()) {

                       Attribute attribute = attributeIterator.next();
                       if(attribute.getName().getLocalPart().equals("floor")) {
                           int floor = Integer.parseInt(attribute.getValue());
                           office.setFloor(floor);
                       }

                       if(attribute.getName().getLocalPart().equals("room")) {
                            int room = Integer.parseInt(attribute.getValue());
                            office.setRoom(room);
                       }
                   }

                }

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

                if(endElement.getName().getLocalPart().equals("office")) {
                    office.setEmployeeList(employeeList);
                    employeeList = new ArrayList<>();
                    officeList.add(office);
                }
            }


        }
    }

    public List<Office> getOffice() {
        return officeList;
    }
}
