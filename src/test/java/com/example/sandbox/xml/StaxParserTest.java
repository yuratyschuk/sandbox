package com.example.sandbox.xml;

import com.example.sandbox.model.Employee;
import com.example.sandbox.xml.util.StaxParserForEmployee;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class StaxParserTest {


    @Test
    @SneakyThrows
    public void parseStudentFile() {

        StaxParserForEmployee staxParserForEmployee = new StaxParserForEmployee();

        staxParserForEmployee.parse("src/test/resources/file_for_parse2.xml");
        List<Employee> employeeList = staxParserForEmployee.getData();

        assertThat(employeeList.size()).isEqualTo(6);

    }
}
