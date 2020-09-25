package com.example.sandbox.assertj;

import com.example.sandbox.assertj.util.FileUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

public class FileTest {

    private final String TEXT_TO_WRITE = "AssertJ test";

    private final String PATH_TO_FILE = "src/main/resources/test.txt";

    private File testFile;

    private FileUtil fileUtil;

    @BeforeEach
    public void setup() {
        testFile = new File(PATH_TO_FILE);
    }

    @Test
    public void testFileExists() {

        assertThat(testFile).exists().isFile();
    }

    @Test
    public void testCreateFileAndWriteToIt() {

        fileUtil = new FileUtil();
        fileUtil.writeToFile(PATH_TO_FILE, TEXT_TO_WRITE);

        assertThat(contentOf(testFile)).contains(TEXT_TO_WRITE);
    }


    @Test
    public void testFileContentWithStartsWithContainsAndEndsWith() {
        assertThat(contentOf(testFile)).startsWith("AssertJ").endsWith("test");

    }

    @Test
    public void testFileContentWithIsEqualTo() {
        assertThat(contentOf(testFile)).isEqualTo("AssertJ test");
    }


}
