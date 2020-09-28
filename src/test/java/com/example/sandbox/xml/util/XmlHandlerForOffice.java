package com.example.sandbox.xml.util;

import com.example.sandbox.model.Employee;
import com.example.sandbox.model.Office;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XmlHandlerForOffice extends DefaultHandler {

    private static final String NAME = "name";

    private static final String JOB = "job";

    private static final String EMPLOYEE = "employee";

    private static final String OFFICE = "office";

    private List<Employee> employeeList = new ArrayList<>();

    private final List<Office> officeList = new ArrayList<>();

    private Office office;

    private String elementValue;


    @Override
    public void characters(char[] ch, int start, int length) {
        elementValue = new String(ch, start, length);
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case OFFICE:
                office = new Office();
                int floor = Integer.parseInt(attributes.getValue("floor"));
                int room = Integer.parseInt(attributes.getValue("room"));
                office.setFloor(floor);
                office.setRoom(room);
            case EMPLOYEE:
                employeeList.add(new Employee());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {

            case NAME:
                latestOffice().setName(elementValue);
                break;
            case JOB:
                latestOffice().setJob(elementValue);
                break;
            case OFFICE:
                office.setEmployeeList(employeeList);
                employeeList = new ArrayList<>();
                officeList.add(office);
        }

    }

    private Employee latestOffice() {
        List<Employee> employees = employeeList;
        int latestEmployeeIndex = 0;
        if (employees.size() > 0) {
            latestEmployeeIndex = employees.size() - 1;
        }
        return employees.get(latestEmployeeIndex);
    }


    public List<Office> getOfficeList() {
        return officeList;
    }
}
