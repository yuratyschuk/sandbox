package com.example.sandbox.xml;

import com.example.sandbox.model.Office;
import com.example.sandbox.xml.util.DomParserForOffice;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DomParserTest {


    @Test
    @SneakyThrows
    public void domParserExample() {
        DomParserForOffice domParserForOffice = new DomParserForOffice();

        domParserForOffice.parse("src/test/resources/file_for_parse2.xml");
        List<Office> officeList = domParserForOffice.getOfficeList();

        assertThat(officeList.size()).isEqualTo(2);

        assertThat(officeList)
                .extracting(Office::getEmployeeList)
                .isNotEmpty();
    }
}
