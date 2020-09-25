package com.example.sandbox.assertj.util;

import lombok.SneakyThrows;

import java.io.FileWriter;

public class FileUtil {

    @SneakyThrows
    public void writeToFile(String path, String text) {
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(text);
        fileWriter.close();
    }
}
