package org.example;

import java.util.Scanner;
public class User {
    private String name;
    Scanner user_input = new Scanner(System.in);

    public User() {
        System.out.println("What is your name?");
        this.name = user_input.next();
    }

    public String getName (){
        return name;
    }
}
