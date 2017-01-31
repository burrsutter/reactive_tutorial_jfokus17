package com.burrsutter.reactiveworkshop;

import rx.Observable;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.TimeUnit;

import java.nio.file.Path;
import java.nio.file.FileSystems;

/**
 * Created by burr on 1/30/17.
 */
public class ObservableReadFile {
    public static void main(String[] args) {

        Path path = FileSystems.getDefault().getPath("/Users/burr/temp/users.json");

        Observable<String> lines;
        try {
            lines = Observable.from(Files.readAllLines(path, StandardCharsets.UTF_8));
        } catch (IOException e) {
            lines = Observable.error(e);
        }
        lines.subscribe(System.out::println);


    }
}
