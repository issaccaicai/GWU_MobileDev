package com.example.lib;

public class Lecture4 {

    public static void main(String[] args) {
        Lecture3 lec3 = new Lecture3();
        lec3.result(78,25,87);
        lec3.setCourseName("Mobile app dev");
        lec3.display();

        Lecture4 lec4 = new Lecture4();
        lec4.Students();


        Dog dog = new Dog();
        dog.setDogNaME("Lady");
        dog.name();
        dog.bark();
        dog.eat();
        dog.run();;

        Dog dog1 = new Dog();
        dog1.setDogNaME("Tramp");
        dog1.name();
        dog1.bark();
        dog1.eat();
        dog1.run();;
    }

    public void Students(){
        String name = "Ying";
        String course = "ISTM 6216";
        System.out.printf("\n%s%s\n%s%s\n","Student Name: ",name, "Course: ", course);
    }

}

