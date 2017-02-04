package com.burrsutter.reactiveworkshop;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BWarmups {

    public static void main(String args[]) {
        System.out.println(BWarmups.class.getSimpleName());
        
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

        // imperative style        
        System.out.println("* Imperative, foreach *");
        for (String greeting : greetings) {
            if (greeting.toUpperCase().startsWith("B")) {
                System.out.println(greeting);
            }          
        }  // for

        // new school
        System.out.println("* New school *");
        greetings.stream()
              .map(String::toUpperCase)
              //.peek(item -> System.out.println(item))
              .filter(symbol -> symbol.startsWith("B"))
              .forEach(System.out::println);

    } // main
}