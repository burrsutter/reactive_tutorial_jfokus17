package com.burrsutter.reactiveworkshop;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.buffer.Buffer;
import rx.Observable;
import io.vertx.core.file.OpenOptions;
import io.vertx.rxjava.core.file.AsyncFile;
/**
 * Created by burr on 1/31/17.
 */
public class ObservableReadFile extends AbstractVerticle {

    public void start() {
        System.out.println("\n\n***Start***\n\n");
        Observable<Buffer> content = vertx.fileSystem()
                .rxOpen("/Users/burr/temp/users.json", new OpenOptions())
                .flatMapObservable(AsyncFile::toObservable);

        content.subscribe(System.out::println);


;    }
}
