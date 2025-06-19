package funct1;


import java.util.function.Function;

public class Main {
    public static Function<Integer, Integer> square = (x) -> x* x;

    public static void main(String[] args) {
        // Print out the address of square
        System.out.println("Address of square: " + square);

        // Define a new variable
        Function<Integer, Integer> other;
        other = square;
        System.out.println("Address of other: " + other);

        // Change the value of other
        other = x -> x * x * x;
        System.out.println("Address of other after reassignment: " + other);
        System.out.println("Address of square: " + square);
    }

}