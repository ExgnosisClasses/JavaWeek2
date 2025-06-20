# Lab 6-1 Java Streams


## Lab objectives
   
In this lab you will write some basic code to filter, sort and print a list of arbitrary string objects using two different approaches: the traditional imperative approach, and then a functional approach using streams, with a few variations on some of the stream code thrown in.

## Lab Setup
   You can do this whole lab in one project with different packages for the imperative and the streams solutions

## Lab Solutions

Solutions for each lab are provided in the repository in the labs directory for each module. 
   - These are not the only possible solutions, and your solution to the labs may be a bit different in some ways than the reference solutions.

---

## Part 1: The Imperative Code

- Create an imperative package (or you can call it whatever you like)
- Add a Main class with a main() method. All of your code will be written inside the main method so you don’t need any other classes
- Create a list of strings to use as the input data. You don’t have to use exactly the data shown, but you should have something similar.
- Create an empty list to hold the results.


```java 
		List<String> randomValues = Arrays.asList(
				"E11", "D12", "A13", "F14", "C15", 
				"A16", "B11", "B12", "C13", "B14",
				"B15", "B16", "F12", "E13", "C11", 
				"C14", "A15", "C16", "F11", "C12",
				"D13", "E14", "D15", "D16");
		
        // Final list of values
		List<String> finalValues = new ArrayList<>();

```

- Write some standard imperative code to select only the strings that start with ‘C’, sort the results and then print each element in turn. 
- Note that we are using a built-in sort method from the Collections API; otherwise the code would be even longer

```java
		// Filter the list
		for (String value : randomValues) {
			if (value.startsWith("C")) {
				finalValues.add(value);
			}
		}
        // Sort the final list and print each individual value
		Collections.sort(finalValues);
		for (String value : finalValues) {
			System.out.println(value);
		}

```

- Test your code to ensure it works. You should see results like this:

```console
C11
C12
C13
C14
C15
C16

Process finished with exit code 0
```

## Part 2: Using Streams

In a new package called  in the solutions the package is named streams, create a Main class to hold a main() method and set up the initial list as before. However, you do not need a list of final values.

```java 
		List<String> randomValues = Arrays.asList(
				"E11", "D12", "A13", "F14", "C15", 
				"A16", "B11", "B12", "C13", "B14",
				"B15", "B16", "F12", "E13", "C11", 
				"C14", "A15", "C16", "F11", "C12",
				"D13", "E14", "D15", "D16");
```

- Now use the .stream() method to create a stream from the List
- Add a .filter() pipeline method that uses a Lambda function to test whether to remove the data item from the stream
- Then add a .sorted() pipeline method that sorts the stream
- Finally, a .forEach() method applies the println() function in the System.out package to each of the elements in the stream and terminates the stream.
- As an alternative to the foreach(System.out:println) is to provide a Lambda function that does the same job in a little more traditional programming format – which you use is matter of taste.

```java
randomValues.stream()      					// Create the streams
	.filter(value->value.startsWith("C"))   	// Filter pipeline method
	.sorted()								 	// Sort pipeline method
	.forEach(System.out::println);	     		// Terminal forEach
    // .forEach(x -> System.out.println(x));
	}

```

Test your code to see if it works. The output should be the same as before


## Part Three: Terminal Collection

In this section, you will change the terminal action to return a collection. 
- You can modify the code from the last part for this part. 
- In the solutions, this code is in the package streams2.
- You will need to add a list to hold the final results.
- The full code looks like this

```java
package streams2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
	
		// Starting list of values
		List<String> randomValues = Arrays.asList(
				"E11", "D12", "A13", "F14", "C15", 
				"A16", "B11", "B12", "C13", "B14",
				"B15", "B16", "F12", "E13", "C11", 
				"C14", "A15", "C16", "F11", "C12",
				"D13", "E14", "D15", "D16");
		
        // Final list of values
		List<String> finalValues = new ArrayList<>();
		
		finalValues = 										// Stream output target
				randomValues.stream()   					// Create the stream
				.filter(value->value.startsWith("C"))   	// Filter pipeline method
				.sorted()								 	// Sort pipeline method
				.collect(Collectors.toList());	     		// Terminal Collect
	
	    finalValues.forEach(System.out::println);
	}
}


```

- Note that the terminal method of the stream no longer prints out the elements, so we have to add the extra line to do that.
- Test your code to ensure that it works.

## End Lab