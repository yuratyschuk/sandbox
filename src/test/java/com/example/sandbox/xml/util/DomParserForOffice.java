package com.example.sandbox.xml.util;

import com.example.sandbox.model.Employee;
import com.example.sandbox.model.Office;
import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DomParserForOffice {

    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

    private final List<Office> officeList = new ArrayList<>();

    public DomParserForOffice() throws ParserConfigurationException {
    }

    @SneakyThrows
    public void parse(String fileName) {

        Document document;
        try(InputStream inputStream = new FileInputStream(fileName)) {
            document = documentBuilder.parse(inputStream);
        }

        NodeList nodeList = document.getElementsByTagName("office");

        for(int i = 0; i < nodeList.getLength(); i++) {
            List<Employee> employeeList = new ArrayList<>();
            Office office = new Office();
            Element element = (Element) nodeList.item(i);

            int floor = Integer.parseInt(element.getAttribute("floor"));
            int room = Integer.parseInt(element.getAttribute("room"));
            office.setFloor(floor);
            office.setRoom(room);

            NodeList nodeListEmployee = document.getElementsByTagName("employee");
            for(int j = 0; j < nodeListEmployee.getLength(); j++) {

                String job = element.getElementsByTagName("job").item(0).getTextContent();
                String name = element.getElementsByTagName("name").item(0).getTextContent();

                employeeList.add(new Employee(job, name));
            }
            office.setEmployeeList(employeeList);
            officeList.add(office);
        }

    }

    public List<Office> getOfficeList() {
        return officeList;
    }
}
