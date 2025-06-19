package funct2;


import java.util.function.Function;

// The decision on the actual code to be executed is deferred to run time by providing
// a variable f - sort of a slot for code - which gets assigned some executable code when
// the function applyFunc is called.



public class Main {
    // Define a few functions
    public static Function<Integer,Integer> identity = x -> x;
    public static Function<Integer,Integer> square = x -> x * x;
    public static Function<Integer,Integer> cube = x -> x * x * x;
    // Note that this has a different type than the preceding three functions
    public static Function<Integer,Double> dsquare = x ->  Double.valueOf(x * x);

    // returns the value of applying function f to the integer x
    private static int applyFunc(int x, Function<Integer,Integer> f) {
        return f.apply(x);
    }

    public static void main(String[] args) {
        System.out.println("Identity for 3 is " + applyFunc(3,identity));
        System.out.println("Square of 3 is " + applyFunc(3,square));
        System.out.println("Cube of 3 is " + applyFunc(3,cube));
        // Passing a Lambda function
        System.out.println("Adding 1 to 78 is " + applyFunc(78,(x) -> x + 1));
        // Note that the following will not compile
        // Type mismatch in the argument
        // System.out.println("Square of 3 is " + applyFunc(3,dsquare));

    }
}
