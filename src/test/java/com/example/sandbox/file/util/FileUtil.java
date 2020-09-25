package com.example.sandbox.file.util;

import lombok.SneakyThrows;

import java.io.FileWriter;

public class FileUtil {

    @SneakyThrows
    public void writeToFile(String path, String text)  {

        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(text);
        }
    }
}
