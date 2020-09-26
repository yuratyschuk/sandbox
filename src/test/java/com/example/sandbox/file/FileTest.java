package com.example.sandbox.file;

import com.example.sandbox.file.util.FileUtil;
import org.testng.annotations.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

public class FileTest {

    private final String TEXT_TO_WRITE = "AssertJ test";

    private final String PATH_TO_FILE = "src/test/resources/test.txt";

    private final File testFile = new File(PATH_TO_FILE);

    private final FileUtil fileUtil = new FileUtil();


    @Test
    public void testFileExists() {

        assertThat(testFile).exists().isFile();
    }

    @Test
    public void testCreateFileAndWriteToIt() {

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
