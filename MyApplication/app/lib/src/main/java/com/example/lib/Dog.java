package com.example.lib;

public class Dog {
    private  String dogNaME;
    public void setDogNaME(String name){
        dogNaME = name;
    }
    public String getDogNaME(){
        return dogNaME;
    }
    public void name(){
        System.out.printf("\nThe dog name is %s!",getDogNaME());

    }
    public void bark(){
        System.out.printf("\n%s\t%s!",getDogNaME(),"bark");

    }
    public void eat(){
        System.out.printf("\n%s\t%s!",getDogNaME(),"eats");

    }
    public void run(){
        System.out.printf("\n%s\t%s!",getDogNaME(),"runs");

    }

}
