# Lab 6-2: Intermediate Methods

## Lab objectives
   The purpose of this lab is to get you familiar with both the stream pipeline methods and the process designing a pipeline.

## Lab Setup
   You can do this whole lab in one project with different packages for the different parts

## Lab Solutions
Solutions for each part are provided in the Solutions folder in the lab directory. 
- These are not the only possible solutions and your solution to the labs may be a different in some ways than the reference solutions.


## Part 1: Map, Filter and Peek

For this part of the lab, generate an unsorted List<Integer> of about 20 integers random between 1 and 10. For example, the lab solution uses

```java
public static void main(String[] args) {
		List<Integer> data = Arrays.asList(
				8, 9, 4, 3, 1, 
				9, 1, 2, 9, 8, 
				5, 3, 4, 10, 6,
				5, 7, 5, 1, 6);
```

Write a stream that implements the following steps
1. Use the operator `peek(x -> System.out.println("First peek " + x))` to display the elements of the stream as they are processed.
2. Filter out all elements that are less than five.
3. Use the operator `peek(x -> System.out.println("Second peek " + x))` to display the elements of the stream after the filter.
4. Replace any occurrence of 9 with a 0.
5. Sort the result.
6. Return the result as a list of integers using `collect(Collectors.toList())`

The solution should look like this

```java
   public static void main(String[] args) {
    List<Integer> data = Arrays.asList(
            8, 9, 4, 3, 1,
            9, 1, 2, 9, 8,
            5, 3, 4, 10, 6,
            5, 7, 5, 1, 6);

    List<Integer> results =
            data.stream()
                    .peek(x -> System.out.println("First peek " + x))
                    .filter(x -> x > 4)
                    .peek(x -> System.out.println("Second peek " + x))
                    .map(x -> (x == 9) ? 0 : x)
                    .sorted()
                    .collect(Collectors.toList());

    System.out.println(results);
}

```

- Print the result to ensure that the code executed correctly
- Notice that when the stream was executed, the peek operation showed that the stream was processed sequentially at each step from the first element to the last
- But processing on an element is terminated as soon as it is filtered out of the stream

Your output should look like this:

```console
First peek 8
Second peek 8
First peek 9
Second peek 9
First peek 4
First peek 3
First peek 1
First peek 9
Second peek 9
First peek 1
First peek 2
First peek 9
Second peek 9
First peek 8
Second peek 8
First peek 5
Second peek 5
First peek 3
First peek 4
First peek 10
Second peek 10
First peek 6
Second peek 6
First peek 5
Second peek 5
First peek 7
Second peek 7
First peek 5
Second peek 5
First peek 1
First peek 6
Second peek 6
[0, 0, 0, 5, 5, 5, 6, 6, 7, 8, 8, 10]

Process finished with exit code 0

```

- Move the filter operator to just after the sorting step and put the second peek just before the filter
- Run the code and see how it processes the streams
- What conclusions can you come to about ordering filters with respect to other methods?

The changes are shown here

```java
   public static void main(String[] args) {
        List<Integer> data = Arrays.asList(
                8, 9, 4, 3, 1,
                9, 1, 2, 9, 8,
                5, 3, 4, 10, 6,
                5, 7, 5, 1, 6);

        List<Integer> results =
				data.stream()
				.peek(x -> System.out.println("First peek " + x))
				.map(x -> (x == 9) ? 0 : x)
				.sorted()
				.peek(x -> System.out.println("Second peek " + x))
				.filter(x -> x > 4)
				.collect(Collectors.toList());

        System.out.println(results);
    }

```

The output should look like this

```console
First peek 8
First peek 9
First peek 4
First peek 3
First peek 1
First peek 9
First peek 1
First peek 2
First peek 9
First peek 8
First peek 5
First peek 3
First peek 4
First peek 10
First peek 6
First peek 5
First peek 7
First peek 5
First peek 1
First peek 6
Second peek 0
Second peek 0
Second peek 0
Second peek 1
Second peek 1
Second peek 1
Second peek 2
Second peek 3
Second peek 3
Second peek 4
Second peek 4
Second peek 5
Second peek 5
Second peek 5
Second peek 6
Second peek 6
Second peek 7
Second peek 8
Second peek 8
Second peek 10
[5, 5, 5, 6, 6, 7, 8, 8, 10]

Process finished with exit code 0
```

## Part 2: More Intermediate Methods

- You can modify the code from the previous part to do this part.
- Write a stream that removes the first 10 elements using skip(10) and print the results
- Try skipping more elements than are in the stream (skip(100)) and see what happens
- Write a stream that process only the first 10 elements in the stream using limit(10)
- Write a stream that removes duplicates using the distinct() operator
- Note that both distinct() and sorted() have to work with all of the elements in the step.
- This is an example of functional programming. We are telling the stream that we want to remove duplicates of sort the stream, but we do not have to supply code to describe how to do these operations.

Check the code in the Solutions folder if you need hints

## Part 3: FlatMap

- Create a list of lists of countries and print it out to ensure you understand the structure of the data


```java


    List<String> asia = Arrays.asList("Japan","Korea", "China", "Laos");
    List<String> europe = Arrays.asList("England","France", "Sweden", "Laos");
    List<String> sa = Arrays.asList("Brazil","Argentia", "Peru", "Guyana");
    List<String> fifa =  Arrays.asList("Brazil","Argentia", "England", "France"); 
    List<String> nato =  Arrays.asList("Sweden", "England", "France");
    
    
    List< List<String> > countries = new ArrayList< List<String> >();
    countries.add(europe);
    countries.add(asia);
    countries.add(sa);
    countries.add(fifa);
    countries.add(nato);
   
    System.out.println(countries);
    

```

- Use `flatMap()` to flatten this into a single stream of strings. 
- The flatMap() operation will have to create a stream of strings from each of the list of strings

```java
    List<String> nations =
    		countries.stream()
    		.flatMap(x -> x.stream())
    		.collect(Collectors.toList());

```

Your output should look like this:

```console
[[England, France, Sweden, Laos], [Japan, Korea, China, Laos], [Brazil, Argentia, Peru, Guyana], [Brazil, Argentia, England, France], [Sweden, England, France]]
[England, France, Sweden, Laos, Japan, Korea, China, Laos, Brazil, Argentia, Peru, Guyana, Brazil, Argentia, England, France, Sweden, England, France]

Process finished with exit code 0
```

Now add the intermediate methods to filter out duplicates and sort the rest.

In the flatmap step can see that the datatype of the input stream elements was `List<String>` but the output stream datatype is `String`
- For each step, all the elements in the stream must be the same type
- But a step can transform the input type into a new type, as long as all the elements in the output stream have been transformed to the same new type

## End Lab