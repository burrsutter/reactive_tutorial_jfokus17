# Purpose

This repository represents the various RxJava and Vert.x examples used in the [Reactive Programing, Systems and Microservices Workshop](https://www.jfokus.se/jfokus17/preso/Reactive-Programming-Systems-and-Microservices.pdf) at [JFokus 2017](www.jfokus.se).

See the readme.md in each directory.  Start at 00 to 09.  
# EC2 Instance Setup
Using RHEL 

## JDK Installation
sudo yum -y install java-1.8.0-openjdk-devel

java -version

javac -version

## Vert.x Installation

sudo yum install wget

wget https://bintray.com/artifact/download/vertx/downloads/vert.x-3.4.1-full.tar.gz

tar -xvzf vert.x-3.4.1-full.tar.gz

export PATH=$PATH:/home/ec2-user/vertx/bin
