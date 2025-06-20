# Lab 5-2 Character Streams


## Lab Overview

In this lab, we will modify the previous lab to read and write a file character by character using the `FileReader` and `FileWriter` interfaces

## Part 1: Setup

Start with the code from the first lab.  Note that the name of the class has been changed from `ByteCopy` to `CharCopy`

Go back to either of the `ByteCopy` labs, record the number of bytes copied.  It should be `4528`


Starter code:

```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CharCopy {
    
	public static void main(String[] args) throws IOException {
		FileInputStream infile = null;
		FileOutputStream outfile = null;
		byte b = 0;
		int byteCount = 0;
		
		try {
			infile = new FileInputStream("SampleText.txt");
			outfile = new FileOutputStream("Copy.txt");
			
			while ((b = (byte)infile.read()) != -1) {
				outfile.write(b);
				byteCount++;
			}
            
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			infile.close();
			outfile.close();
		}
    System.out.println(byteCount + " bytes copied");
	}
}
```
## Part 2: Change the Code

The first code change is to convert it IO classes to `FileReader` and `FileWriter` and to use the variables `c` to hold a character. 
- We are not using a `char` type since the interface returns an `int`. The variable `charCount` records the number of characters processed

```java
 	public static void main(String[] args) throws IOException {
		
		FileReader infile = null;
		FileWriter outfile = null;
		int c = 0;
		int charCount = 0;
```

We can't just open a text file anymore without knowing how it's encoded. 
- We are specifying here that the input and output files will be UTF-8 encoded. 
- If we do not supply an encoding, it will default to whatever your platform defaults to. 
- Normally, this is UTF-8 but it could also be ASCII or anything else.
- Otherwise, it works the same as for the `ByteCopy` class. 
-  The difference is that the `FileReader` and `FileWriter` classes handle the mapping from byte to code point or character and back again.

```java
		try {
			infile = new FileReader("SampleText.txt", StandardCharsets.UTF_8);
			outfile = new FileWriter("Copy.txt", StandardCharsets.UTF_8);
			
			while ((c = infile.read()) != -1) {
				outfile.write(c);
				charCount++;
				
			}
			
		    } catch (IOException e) {
			    System.out.println(e);
			
		    } finally {
			    if (infile != null) infile.close();
			    if (outfile != null) outfile.close();
			
        }
        System.out.println(charCount + " characters copied");
	}
```

The last line prints out the number of characters. In this case, the 4899 bytes is 2512 characters. 
- Notice that this is different from the number of bytes since some characters ar more than one byte long
- The final code is int `part1.java` in the Solutions folder

- Your output should look something like this

```console
2588 characters copied

Process finished with exit code 0
```

## Part 3: Using an array 

The following code is the character equivalent to the byte stream array. 
- Note that the code is almost the same as the byte array
- The code is in the file `part2.java` in the Solutions folder

```java

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

public class CharCopy {
	

	public static void main(String[] args) throws IOException {
		
		FileReader infile = null;
		FileWriter outfile = null;
		
		char [] c = new char[128];
		
		int charCount = 0;
		int charsRead = 0;
		int readCount = 0;
		
		try {
			infile = new FileReader("SampleText.txt", StandardCharsets.UTF_8);
			outfile = new FileWriter("Copy.txt", StandardCharsets.UTF_8);
			
			while ((charsRead = infile.read(c)) != -1) {
				outfile.write(c);
				
				charCount = charCount + charsRead;
				readCount++;
				System.out.println("Read " + readCount + " Chars read " + charsRead);
				
			}
			
		} catch (IOException e) {
			System.out.println(e);
			
		} finally {
			if (infile != null) infile.close();
			if (outfile != null) outfile.close();
			
		}
        System.out.println(charCount + " characters copied");
	}

}
```

Your output should look like this

```console
Read 1 Chars read 128
Read 2 Chars read 128
Read 3 Chars read 128
Read 4 Chars read 128
Read 5 Chars read 128
Read 6 Chars read 128
Read 7 Chars read 128
Read 8 Chars read 128
Read 9 Chars read 128
Read 10 Chars read 128
Read 11 Chars read 128
Read 12 Chars read 128
Read 13 Chars read 128
Read 14 Chars read 128
Read 15 Chars read 128
Read 16 Chars read 128
Read 17 Chars read 128
Read 18 Chars read 128
Read 19 Chars read 128
Read 20 Chars read 128
Read 21 Chars read 28
2588 characters copied

Process finished with exit code 0
```

---

## End Lab
