package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //System.out.println(persons.toString());

        long children = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println("Quantity of children: " + children);

        List<String> soliders = persons.stream()
                .filter(p -> (p.getAge() >= 18 && p.getAge() < 27) && p.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .limit(15)
                .toList();
        System.out.println("List of soliders: " + soliders);

        List<String> workers = persons.stream()
                .filter(education -> education.getEducation().equals(Education.HIGHER))
                .filter(p -> (p.getSex().equals(Sex.MAN) ? (p.getAge() >= 18 && p.getAge() < 65) : (p.getAge() >= 18 && p.getAge() < 60)))
                .map(Person::getFamily)
                .limit(15)
                .sorted(Comparator.naturalOrder())
                .toList();
        System.out.println("List of workers: " + workers);

    }
}
