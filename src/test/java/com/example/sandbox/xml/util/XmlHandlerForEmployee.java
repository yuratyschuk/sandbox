package com.example.sandbox.xml.util;

import com.example.sandbox.model.Employee;
import lombok.extern.log4j.Log4j2;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class XmlHandlerForEmployee extends DefaultHandler {

    private final String NAME = "name";

    private final String JOB = "job";

    private final String EMPLOYEE = "employee";

    private final String EMPLOYEES = "employees";

    private List<Employee> employeeList = new ArrayList<>();
    private String elementValue;

    private int firstNameValue = 0;


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case EMPLOYEE:
                employeeList.add(new Employee());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {


            case NAME:
                if(firstNameValue > 0) {
                    latestOffice().setName(elementValue);
                }
                firstNameValue++;
                break;
            case JOB:
                latestOffice().setJob(elementValue);
                break;

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

    public List<Employee> getData() {
        return employeeList;
    }

}
