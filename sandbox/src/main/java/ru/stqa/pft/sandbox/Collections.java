package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String args[]) {
        //массив
        String[] langs = {"Java", "C#", "Python", "PHP"};
        for (String l : langs) {
            System.out.println("Я хочу выучить " + l);
        }

        //список
        List<String> languages = Arrays.asList("C#", "Java", "Python", "PHP");
        for (String l : languages) {
            System.out.println("Я очень хочу выучить " + l);
        }
    }
}
