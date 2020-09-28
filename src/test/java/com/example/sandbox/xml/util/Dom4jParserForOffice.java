package com.example.sandbox.xml.util;

import com.example.sandbox.model.Employee;
import com.example.sandbox.model.Office;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Dom4jParserForOffice {

    private final List<Office> officeList = new ArrayList<>();

    public void parse(String fileName) throws DocumentException {
        SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(fileName);
        List<Node> nodeList = document.selectNodes("//office");

        for (Node node : nodeList) {
            Office office = new Office();
            Element element = (Element) node;

            int floor = Integer.parseInt(element.attributeValue("floor"));
            int room = Integer.parseInt(element.attributeValue("room"));
            office.setFloor(floor);
            office.setRoom(room);

            List<Node> nodeListEmployee = element.selectNodes("*/employee");

            List<Employee> employeeList = new ArrayList<>();
            for (Node value : nodeListEmployee) {
                Element elementEmployee = (Element) value;

                String name = elementEmployee.selectSingleNode("name").getStringValue();
                String job = elementEmployee.selectSingleNode("job").getStringValue();

                employeeList.add(new Employee(name, job));
            }

            office.setEmployeeList(employeeList);
            officeList.add(office);
        }
    }

    public List<Office> getOfficeList() {
        return officeList;
    }
}
