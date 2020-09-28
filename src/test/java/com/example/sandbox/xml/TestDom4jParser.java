package com.example.sandbox.xml;

import com.example.sandbox.model.Office;
import com.example.sandbox.xml.util.Dom4jParserForOffice;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestDom4jParser {


    @Test
    @SneakyThrows
    public void testEmployeeParser() {
        Dom4jParserForOffice dom4jParserForOffice = new Dom4jParserForOffice();

        dom4jParserForOffice.parse("src/test/resources/file_for_parse2.xml");

        List<Office> officeList = dom4jParserForOffice.getOfficeList();

        assertThat(officeList.size()).isEqualTo(2);

        assertThat(officeList)
                .extracting(Office::getEmployeeList)
                .isNotEmpty();
    }
}
