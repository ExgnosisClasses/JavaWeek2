# Lab 5-3 Buffered IO

## Lab Overview

In this lab, you will implement a BufferedReader and BufferedWriter to perform the same file copy that you did in the previous labs.


## Part 1: Setup

This code will look the similar to the code from the previous two labs.
- If the `Copy.txt` file exists from a previous lab delete it.

## Part 2: Implementing the Code

The buffered forms of `FileReader` and `FileWriter` take objects of these types and add buffering capabilities.  We also need a `String` variable to hold the input string.

```java
public static void main(String[] args) throws IOException {
		
	FileReader infile = null;
	FileWriter outfile = null;
	BufferedReader inbuff = null;
	BufferedWriter outbuff = null;
		
	String line = null;
```

Create the buffered reader and writer.


```java
try {
	infile = new FileReader("SampleText.txt", StandardCharsets.UTF_8);
	inbuff = new BufferedReader(infile);
	outfile = new FileWriter("Copy.txt", StandardCharsets.UTF_8);
	outbuff = new BufferedWriter(outfile);

```
Now read a line at a time until the end of the file. When writing the output, remember that the input reader only reads up to the end-of-line character so it is not in the input buffer. It has to be added when we output the file.

```java
try {
	infile = new FileReader("SampleText.txt", StandardCharsets.UTF_8);
	inbuff = new BufferedReader(infile);
	outfile = new FileWriter("Copy.txt", StandardCharsets.UTF_8);
	outbuff = new BufferedWriter(outfile);
			
	while ((line = inbuff.readLine()) != null) {
		outbuff.write(line);
		outbuff.newLine();
		System.out.println("Line = "+ line) ;
	}

```

Close the files but be sure to flush the output buffer before you do.


```java
try {
	infile = new FileReader("SampleText.txt", StandardCharsets.UTF_8);
	inbuff = new BufferedReader(infile);
	outfile = new FileWriter("Copy.txt", StandardCharsets.UTF_8);
	outbuff = new BufferedWriter(outfile);
	
	while ((line = inbuff.readLine()) != null) {
  	outbuff.write(line);
		outbuff.newLine();
		System.out.println("Line = "+ line) ;
		}
			
} catch (IOException e) {
	System.out.println(e);
	
} finally {
	outbuff.flush();
	if (inbuff != null) inbuff.close();
	if (outbuff != null) outbuff.close();	
}

```

The full code in the file `buffer.java` in the Solutions folder

Check to see that the `Copy.txt` file was created correctly

---

## End Lab



