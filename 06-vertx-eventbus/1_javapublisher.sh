#!/bin/bash
sudo env "PATH=$PATH" vertx run src/main/java/MyPublisher.java -cluster
