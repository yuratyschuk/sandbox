package com.example.sandbox.assertj;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class FileTest {

    private File testFile = new File("src/main/resources/test.txt");

    private final String FILE_TEXT = "AssertJ test";
    @Test
    public void testFileExists() {

        assertThat(testFile).exists().isFile();
    }

    @Test
    public void testFileContentWithStartsWithContainsAndEndsWith() {
        assertThat(contentOf(testFile)).startsWith("This").contains("is a text file").endsWith("to test");

    }

    @Test
    public void testFileContentWithIsEqualTo() {
        assertThat(contentOf(testFile)).isEqualTo("This is a text file to test");
    }

    @Test
    public void testCreateFileAndWriteToIt() {

        writeToFile();

        File file = new File("src/main/resources/testFileToWrite.txt");
        assertThat(contentOf(file)).contains(FILE_TEXT);
    }

    @SneakyThrows
    private void writeToFile() {
        FileWriter fileWriter = new FileWriter("src/main/resources/testFileToWrite.txt");
        fileWriter.write(FILE_TEXT);
        fileWriter.close();
    }
}
