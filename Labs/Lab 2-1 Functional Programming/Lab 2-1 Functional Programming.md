# Lab 2-1 Functional Programming

## Lab Objectives

This first lab is designed to help get started with the basics of using functions as first class objects.

## Set Up

This lab assumes you will be working in a package called `funct1`

## Part 1: Create some functions

- Remember that Java does not support global variables. 
- We can use variables that hold functions in the same places we create variables that hold data. 
- These are: 
  - As Instance variables in a class definition
  - As local variables in a method body
  - As a static variable in a class definition.
  
In this lab, only the last two are used. 
- The static variables are in the Main class in the solutions, but you can put them in any class you want.

Create five variables to hold functions of different types:
1. A variable called square that holds a function that takes an Integer argument and returns an integer
2. A variable called isEven that holds a predicate that takes an Integer argument.
3. A variable called sum that takes two Integers and arguments and returns an Integer. 
4. A variable called today that holds a Supplier that returns a String.
5. A variable called printLength that takes a string argument.
   - Initialize the printLength method with the function body `(s) -> System.out.print(s.length())`


```java
public class Main {
	
// Define several static variables to hold different function types	
	public static Function<Integer,Integer> square;
	public static Predicate<Integer> isEven;
	public static BiFunction<Integer,Integer,Integer> sum;
	public static Supplier<String> today;
	// This variable is initialized
	public static Consumer<String> printLength = (s) -> System.out.print(s.length());
	

```

In the body of the main method, assign the Lambda functions to the variables as shown. 
- Then use each function with some test data and the appropriate `apply()`, `test()`, `get()` or `accept()` method. 
- Print out the result of executing each function as shown below:

```java
public static void main(String[] args) {
        // Assign the variables their values in the form of Lambda expressions
        // since the lambda expression is one line, we omit the {} by convention
        square = (x) -> x * x ;
        System.out.println("The square of 7 is " + square.apply(7));

        isEven = (x) -> 0 == x % 2;
        System.out.println("Test to see if 5 is even " + isEven.test(5));

        sum = (x,y) -> x + y;
        System.out.println("The sum of 45 and 78 is " + sum.apply(45,78));

        today = () -> LocalDate.now().toString();
        System.out.println("Today is " + today.get());

        System.out.print ("The length of the string \"Hello World\" is ");
        printLength.accept("Hello World");
    }

```

Your output should look like this:

```console
The square of 7 is 49
Test to see if 5 is even false
The sum of 45 and 78 is 123
Today is 2025-06-19
The length of the string "Hello World" is 11
Process finished with exit code 0
```

This code is available in the `part1.java` file in the Solutions folder.


## Part 2: Using functions as data

Create a new local function variable which is of type `Function<Integer,Integer>` called `other`.
- Assign the variable `square` to `other`.  
- Print out the address of each variable as shown below. 
- Notice that both variables contain exactly the same function body located at the same address.
- Assign a new Lambda function to `other` and print out the address

```java

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

```

Your output should look like this:

```console
Address of square: funct1.Main$$Lambda/0x00007f598f003548@5caf905d
Address of other: funct1.Main$$Lambda/0x00007f598f003548@5caf905d
Address of other after reassignment: funct1.Main$$Lambda/0x00007f598f003798@8efb846
Address of square: funct1.Main$$Lambda/0x00007f598f003548@5caf905d

Process finished with exit code 0

```
- Java keeps track of the types of the data objects stored on the heap
- The prefix `funct1.Main` identifies where in the code the object is created
- The `$$Lambs` tells us that the data types stored there is a function body

This code is available in `part2.java` in the Solutions folder

## End Lab