package com.example.sandbox.xml;

import com.example.sandbox.model.Employee;
import com.example.sandbox.xml.util.DomParserForEmployee;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DomParserTest {


    @Test
    @SneakyThrows
    public void domParserExample() {
        DomParserForEmployee domParserForEmployee = new DomParserForEmployee();

        domParserForEmployee.parse("src/test/resources/file_for_parse2.xml");
        List<Employee> employeeList = domParserForEmployee.getData();

        assertThat(employeeList.size()).isEqualTo(6);

    }
}
