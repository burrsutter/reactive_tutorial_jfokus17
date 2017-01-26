package com.burrsutter.reactiveworkshop;

import java.util.List;
import java.util.Arrays;
import java.util.Iterator;
public class AWarmups {

    public static void main(String args[]) {
        System.out.println(AWarmups.class.getSimpleName());
        
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

        // old school
        System.out.println("* Old School *");
        Iterator<String> iterator = greetings.iterator();
        while (iterator.hasNext()) {
          String greeting = (String) iterator.next();
          System.out.println(greeting.toUpperCase());
        }

        // imperative style        
        System.out.println("* Imperative, foreach *");
        for (String greeting : greetings) {
          System.out.println(greeting.toUpperCase());
        }  // for
        
        // new school
        System.out.println("* New school *");
        greetings.stream()
            .map(String::toUpperCase)
            .forEach(System.out::println);
              

    } // main
}