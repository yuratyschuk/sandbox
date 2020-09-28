package com.example.sandbox.xml;

import com.example.sandbox.model.Office;
import com.example.sandbox.xml.util.StaxParserForOffice;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestStaxParser {


    @Test
    @SneakyThrows
    public void parseStudentFile() {

        StaxParserForOffice staxParserForOffice = new StaxParserForOffice();

        staxParserForOffice.parse("src/test/resources/file_for_parse2.xml");
        List<Office> officeList = staxParserForOffice.getOffice();

        assertThat(officeList.size()).isEqualTo(2);

        assertThat(officeList)
                .extracting(Office::getEmployeeList)
                .isNotEmpty();
    }


}
