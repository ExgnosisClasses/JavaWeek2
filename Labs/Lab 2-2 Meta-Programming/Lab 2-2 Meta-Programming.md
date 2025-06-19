# Lab 2-2 Meta Programming

## Lab Objectives
This lab is designed to help get started with the basics of using functions as parameters to other functions and the basics of using functions as return to other functions.

The solutions provided all use the package `funct2`

## Part 1: Create some functions

Create some functions to pass as parameters. 
- Typically, the sorts of functions that tend to pass are brief snippets of logic. 
- We create more complex functions by assembling smaller bits of functional code. 
- The chunks of functional code are normally Lambda functions that can be written in one line or two. 
- This is often called a UNIX architecture because this is how the UNIX OS was designed, complex operations are created by using small single function utilities, like grep, which are chained together using pipes.

Create four variables to hold functions of different types:
1. A variable called `square` that holds a function that takes an Integer argument and returns an integer
2. A variable called `isEven` that holds a predicate that takes an Integer argument.
3. A variable called `sum` that takes two Integers and arguments and returns an Integer.
4. A variable called `dsquare` that takes an Integer and returns the square but as a Double

```java

public class Main {
    // Define a few functions
    public static Function<Integer,Integer> identity = x -> x;
    public static Function<Integer,Integer> square = x -> x * x;
    public static Function<Integer,Integer> cube = x -> x * x * x;
    // Note that this has a different type than the preceding three functions
    public static Function<Integer,Double> dsquare = x ->  Double.valueOf(x * x);
```

## Part 2: Create a meta function

A meta-function is a function that can take on different forms at run time. 
- This is different from the process that Java uses for generics to create something like a generic function. 
- For generics, the generic is expanded at compile time, not at run time.
- Meta-functions typically modify their code during execution. 
- Many languages, like LISP, have very sophisticated met-programming systems, but Java can emulate some of this functionality using functional programming.

Our meta-function is called `applyFunc(..)` and takes a data item, in this case an integer, and performs a transformation to it by applying a provided function to that data and then returning the transformed data
- Which transformation is applied depends on what function is passed when the code is executed. 

A typical use case for this would be when some function has to be applied to incoming data, but determining which function should be applied can't be known until runtime when we actually see the data.
- This can also be done with a massive `select` block where each case contains one of the code alternatives, but in general, the functional approach is considered ore elegant and efficient


```java
private static int applyFunc(int x, Function<Integer,Integer> f) {
        return f.apply(x);
    }
```

## Part 3: Test the function

Now call the meta-function using the different functions you have defined earlier. 
- You can also experiment with passing a Lambda function directly.

```java

public static void main(String[] args) {
    System.out.println("Identity for 3 is " + applyFunc(3, identity));
    System.out.println("Square of 3 is " + applyFunc(3, square));
    System.out.println("Cube of 3 is " + applyFunc(3, cube));
    // Passing a Lambda function
    System.out.println("Adding 1 to 78 is " + applyFunc(78, (x) -> x + 1));
    // Note that the following will not compile
    // Type mismatch in the argument
    // System.out.println("Square of 3 is " + applyFunc(3,dsquare));

}   
```

The solution for this section is in the file `part2.java` in the Solutions directory.


## Part 4: Functions as return values

This is also a form of meta-programming, but in this case, we have to choose between several different implementations of a function depending on some condition in the run time environment. 
- In its simplest form, a dispatch table is a function factory that, when invoked, returns the appropriate function to be applied.
- Note that this is the converse to the last section, the meta-function was passed code to execute and return a result. 
- In this section, the dispatch table is passed some data and returns the function that the caller can execute. \
- Dispatch tables  have been common for decades in system programming, especially operating system design, but traditionally used something called function pointers rather than Lambda functions.

- - What it does is takes an integer representing and exponent and returns a function that can be used to raise a value to that exponent. 
- Notice that only the powers 1,2,3 are supported and anything else is raised to the power 0.
- A common use for this construct is to have a bunch of different functions that represent responses to a run time situation.
- The calling function sends the table a code and gets back the function that should be executed in response

## Part 5: Create the dispatch table

Create the functions that can potentially be returned and a function `f` that returns the right function when given an exponent value. 
- In this case, we are returning a function that returns a 0 if the exponent is not 1, 2 or 3.

```java
public class Main {
    public static Function<Integer, Integer> identity = x -> x;
    public static Function<Integer, Integer> square = x -> x * x;
    public static Function<Integer, Integer> cube = x -> x * x * x;

    // Same function from previous demo
    private static int applyFunc(int x, Function<Integer, Integer> f) {
        return f.apply(x);
    }

    // This will create a function based on the power requested. Note 
    // that the default case returns a Lambda that matches the return value
    public static Function<Integer, Integer> f(int power) {
        switch (power) {
            case 1:
                return identity;
            case 2:
                return square;
            case 3:
                return cube;
            default:
                return (x) -> 0; // if out of range, just return a function that returns 0
        }
    }
}
	
```

Now test the function.

- Print out the addresses of a couple of the returned functions to see they are Lambdas

In this case you can use the `applyfunc()` from the last section to applu the ret

```java 
   public static void main(String[] args) {
    // print out the function addresses
    System.out.println("Identity =" + identity + " : f = " + f(1));
    System.out.println("Default f = " + f(0));
    // Now use the return value to pass to the applyFunc function
    // Note that we are not using the created function immediately
    // we will use three a s the value to be "powered"
    for (int i = -1; i < 5; i++) {
        System.out.println("Calling table with exponent value " + i);
        Function<Integer, Integer> newFunc = f(i);
        System.out.println("Computed Value is " + applyFunc(3, newFunc));

    }
}
```

Your output should look like this

```console
Identity =funct2.Main$$Lambda/0x00007f6f3f003548@8efb846 : f = funct2.Main$$Lambda/0x00007f6f3f003548@8efb846
Default f = funct2.Main$$Lambda/0x00007f6f3f003c38@452b3a41
Calling table with exponent value -1
Computed Value is 0
Calling table with exponent value 0
Computed Value is 0
Calling table with exponent value 1
Computed Value is 3
Calling table with exponent value 2
Computed Value is 9
Calling table with exponent value 3
Computed Value is 27
Calling table with exponent value 4
Computed Value is 0

Process finished with exit code 0
```

The code for this is in the file `part3.java` in the solutions folder