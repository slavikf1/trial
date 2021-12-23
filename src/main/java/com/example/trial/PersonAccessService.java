package com.example.trial;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PersonAccessService {

    private static List<Person> DB = new ArrayList<>();

    public int insertPerson(Person person){
//        DB.add(new Person(UUID.randomUUID(), person.getName()));
        DB.add(person);
        return 1;
    }

    public static List<Person> getDB() {
        return DB;
    }
}
