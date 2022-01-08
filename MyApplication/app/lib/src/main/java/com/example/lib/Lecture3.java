package com.example.lib;
import java.util.*;

public class Lecture3 {
    private  String courseName;
    public void setCourseName(String name){
        courseName = name;
    }
    public String getCourseName(){
        return courseName;
    }
    public void display(){
        System.out.printf("Welcome to the grade book for\n%s!\n", getCourseName());
    }
    public void result(int num1,int num2, int num3){

//        int num1 = 78;
//        int num2 = 25;
//        int num3 = 87;

//       Precedence of operators:
//       1. *, /, % Do all multiplications, divisions and remainders from left to right.
//       2.  +, - Next do additions and subtractions from left to right.
//       3. <, >, >=, <= Then any comparisons for relative size.
//       4. ==, != Then do any comparisons for equality and inequality
//       5. = Finally assign the right-hand side to the left-hand side

//initializing numbers to compare
        int a=40, b=78, c=19;
//comparing numbers, a with b and a with c
//if both conditions are true, prints a
        if(a>=b && a>=c)
            System.out.println(a+" is the largest Number");
//comparing b with a and b with c
//if both conditions are true, prints b
        else if (b>=a && b>=c)
            System.out.println(b+" is the largest Number");
        else
//prints c if the above conditions are false
            System.out.println(c+" is the largest number");

        if(num1 < num2){
            if(num2 < num3){
                System.out.println(num3);
            }
            else if(num2 > num3){
                System.out.println(num2);
            }
            else{
                System.out.println("Num2 and num3 have the same value: " + num2);
            }
        }
        else if(num1 > num2){
            if(num1 < num3){
                System.out.println(num3);
            }
            else if(num1 > num3){
                System.out.println(num1);
            }
            else{
                System.out.println("Num1 and num3 have the same value: " + num1);
            }
        }
        else{
            if(num1 < num3){
                System.out.println(num3);
            }
            else if(num1 > num3){
                System.out.println("Num1 and num2 have the same value: " + num1);
            }
            else{
                System.out.println("Num1, num2 and num3 have the same value: " + num1);
            }
        }
    }

//    public static void main(String[] args) {
//        System.out.printf("%s\n%s\n",
//                "Hello:","Ying");
//        System.out.println();
//
//// %s <- message
////Welcome to
////10
//        System.out.printf("%s\n%s\n", "Welcome to", 10);
//        System.out.println();
//
//// %d <- integer
////Welcome
////10
//        System.out.printf("%s\n%d\n", "Welcome", 10);
//        System.out.println();
//
//// %f <- decimal number (float)
////Welcome
////10.200000
//// %0.2f <- keep 2 digits
//        System.out.printf("%s\n%f\n", "Welcome", 10.2);
//        System.out.printf("%.2f\n", 10.2);


/*        Scanner Sc = new Scanner(System.in);  // Create a Scanner object
        System.out.print("Enter the fist number: ");
        int num1 = Sc.nextInt();  // Read user input

        System.out.print("Enter the Second number: ");
        int num2 = Sc.nextInt();

        System.out.print("Enter the Third number: ");
        int num3 = Sc.nextInt();*/



//    }
}

