package com.example.sandbox.xml.util;

import com.example.sandbox.model.Employee;
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

public class DomParserForEmployee {

    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

    private final List<Employee> employeeList = new ArrayList<>();

    public DomParserForEmployee() throws ParserConfigurationException {
    }

    @SneakyThrows
    public void parse(String fileName) {
        Document document;
        try(InputStream inputStream = new FileInputStream(fileName)) {
            document = documentBuilder.parse(inputStream);
        }
        NodeList nodeList = document.getElementsByTagName("employee");
        for(int i = 0; i < nodeList.getLength(); i++) {

            Element element = (Element) nodeList.item(i);

            String job = element.getElementsByTagName("job").item(0).getTextContent();
            String name = element.getElementsByTagName("name").item(0).getTextContent();

            employeeList.add(new Employee(job, name));
        }
    }

    public List<Employee> getData() {
        return employeeList;
    }
}
