package funct2;

import java.util.function.Function;

// Demonstrates a dispatch table but the code for f is quite kludgy
public class Main {
    public static Function<Integer,Integer> identity = x -> x;
    public static Function<Integer,Integer> square = x -> x * x;
    public static Function<Integer,Integer> cube = x -> x * x * x;

    // Same function from previous demo
    private static int applyFunc(int x, Function<Integer,Integer> f) {
        return f.apply(x);
    }
    // This will create a function based on the power requested. Note
    // that the default case returns a Lambda that matches the return value
    public static Function<Integer,Integer> f(int power) {
        switch (power) {
            case 1:
                return identity;
            case 2:
                return square;
            case 3:
                return cube;
            default:
                return (x) -> 0;
        }
    }

    public static void main(String[] args) {
        // print out the function addresses
        System.out.println("Identity =" + identity + " : f = " + f(1));
        System.out.println("Default f = " + f(0));
        // Now use the return value to pass to the applyFunc function
        // Note that we are not using the created function immediately
        // we will use three a s the value to be "powered"
        for (int i = -1; i < 5; i++) {
            System.out.println("Calling table with exponent value " + i);
            Function<Integer,Integer> newFunc = f(i);
            System.out.println("Computed Value is " + applyFunc(3,newFunc));

        }


    }

}
