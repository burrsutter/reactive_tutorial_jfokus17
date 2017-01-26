package com.burrsutter.reactiveworkshop;

import rx.Observable;
import java.util.List;
import java.util.Arrays;

public class Hello04 {

    public static void main(String args[]) {
        System.out.println(Hello04.class.getSimpleName());

        System.out.println("* from()  *");
        List<String> greetings = Arrays.asList(       
            "Hallå",
            "Hello",
            "Bonjour",
            "Hey Ya\'ll",
            "Hola",
            "Ola",
            "Aloha",
            "Howdy",
            "Wassup",
            "Hallo",  
            "Ciao",
            "Namaste"
        );
        Observable.from(greetings).subscribe(System.out::println);
            
    }
}