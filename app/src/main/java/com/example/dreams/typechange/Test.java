package com.example.dreams.typechange;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by likaiyu on 2020/8/18.
 * Java 逆变协变测试
 */

class Test {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("人"));
        persons.add(new Coder("码农"));
        persons.add(new Doctor("医生"));
        persons.add(new Engineer("工程师1"));

        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder("程序员"));
        coders.add(new Engineer("工程师2"));

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("专家"));

        startWorkExtends(persons);
        startWorkExtends(coders);
        startWorkExtends(doctors);

        startWorkSuper(persons);
        startWorkSuper(coders);

    }

    private static void startWorkExtends(List<? extends Person> persons) {
        for(Person person : persons){
            person.work();
        }
    }

    private static void startWorkSuper(List<? super Coder> coders) {
        coders.add(new Coder("coder"));
        coders.add(new Engineer("engineer"));
        for (Object coder : coders) {
            System.out.println(coder.toString());
        }
    }




}
