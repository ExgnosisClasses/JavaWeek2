# Lab 5-4 Byte Streams

## Lab Objectives

In the first part of this lab you will be copying a file byte by byte using FileInputStream and FileOutputStream

In the second part, you will modify the code to use a byte array7.

## Part 1: The input file

- Copy the sample data file from the lab folder in the repository into the root of the project directory. 
- If you put it into any other location, your code will not be able to find it and will throw an IOException when an attempt is made to open it.
- Open the file in a text editor and notice it contains a number of characters in different alphabets. Close the file
- You can create your own file to use as sample input, but you should ensure that it is a UTF-8 file if you want to use it in the next lab
- Note: the output file will not be visible in the project explorer since it is not added to the list of files tracked by the project manifest when it is created. To see your output file, you have to look at the project directory with the Windows file explore

## Part 3: The byte copying code


Create a `ByteCopy` class as the Runner along with a `main()` method
- Add the notation that the main class throws an IOException. This is not really a good practice – to have an unhandled IOException, but this little shortcut will make coding the lab simpler.

```java
public class ByteCopy {


    public static void main(String[] args) throws IOException {
    }
}   

```

- Create a try-catch-finally block with the catch clause catching an IOException
- Ignore the error saying that the exception is never thrown, this is because we don’t have any code in the try block.
- Don't remove the `throws` annotation since we will need it later


```java
public class ByteCopy {
    
    public static void main(String[] args) throws IOException {
        
        try {
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            
        }
    }
} 

```

- Good programming practice is to close every file that is opened. 
- Add the open file code in the try block and the close file operation in the finally block – the reason for placing this in the finally block will be clear soon.
- The `bytecount` variable will keep track of the number of bytes read.
- The byte variable `b` is where we will keep the input


```java

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteCopy {
    
    public static void main(String[] args) throws IOException {

        FileInputStream infile = null;
        FileOutputStream outfile = null;
        byte b = 0;
        int byteCount = 0;
        
        try {
            infile = new FileInputStream("SampleText.txt");
            outfile = new FileOutputStream("Copy.txt");
        } catch (IOException e) {
            System.out.println(e);
        } finally {
             if (infile != null) infile.close();
             if (outfile != null) outfile.close();
        }
    }
} 

```

- Now add the code that does the actual reading and writing.
- And print the final result
- Since closing a file could throw an IOException, to make things easier, we won't add a handler for it but will just pass it up to the calling program


```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteCopy {
    
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
            System.out.println(e);
        } finally {
             if (infile != null) infile.close();
             if (outfile != null) outfile.close();
        }
        System.out.println(byteCount + " bytes copied");
    }
} 
```

- You should see the output

```console
4528 bytes copied

Process finished with exit code 0
```

- Check using OS file system explorer that the `Copy.txt` file is there and the same as the original.
- You may not see it in your IDE because it wasn't added to the project.


## Part 2: Using a Byte Array

In this section, you will replace the byte by byte read with a buffer read

Make the following changes to your code
1. Change the single byte to a 128 size byte array.
2. Add a couple of variables so you can track how many times file reads are done. The inputCount variable will count the number of times a chunk of data is read.
3. Since you now reading a chunk of data, the read method will return the number of byes read into the buffer, not the value that was read.

Your modified code should look like this:
- The code is in file `part2.java` in the solutions folder


```java 

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteCopy {
	

	public static void main(String[] args) throws IOException {
		
		FileInputStream infile = null;
		FileOutputStream outfile = null;
		
		byte[] b = new byte[128];
		
		int inputCount = 0;
		int byteCount = 0;
		int bytesRead = 0;
		
		try {
			infile = new FileInputStream("SampleText.txt");
			outfile = new FileOutputStream("Copy.txt");
			
			while ((bytesRead = infile.read(b)) != -1){
				outfile.write(b);
				
				byteCount = byteCount + bytesRead;
				inputCount++;
				System.out.println("inputCount=" + inputCount + " bytesRead =" + bytesRead );
			}
			
		} catch (IOException e) {
			System.out.println(e);
			
		} finally {
			infile.close();
			outfile.close();
			
		}
        System.out.println(byteCount + " bytes copied");
	}

}
```

Your output should look like this:

```console
inputCount=1 bytesRead =128
inputCount=2 bytesRead =128
inputCount=3 bytesRead =128
inputCount=4 bytesRead =128
inputCount=5 bytesRead =128
inputCount=6 bytesRead =128
inputCount=7 bytesRead =128
inputCount=8 bytesRead =128
inputCount=9 bytesRead =128
inputCount=10 bytesRead =128
inputCount=11 bytesRead =128
inputCount=12 bytesRead =128
inputCount=13 bytesRead =128
inputCount=14 bytesRead =128
inputCount=15 bytesRead =128
inputCount=16 bytesRead =128
inputCount=17 bytesRead =128
inputCount=18 bytesRead =128
inputCount=19 bytesRead =128
inputCount=20 bytesRead =128
inputCount=21 bytesRead =128
inputCount=22 bytesRead =128
inputCount=23 bytesRead =128
inputCount=24 bytesRead =128
inputCount=25 bytesRead =128
inputCount=26 bytesRead =128
inputCount=27 bytesRead =128
inputCount=28 bytesRead =128
inputCount=29 bytesRead =128
inputCount=30 bytesRead =128
inputCount=31 bytesRead =128
inputCount=32 bytesRead =128
inputCount=33 bytesRead =128
inputCount=34 bytesRead =128
inputCount=35 bytesRead =128
inputCount=36 bytesRead =48
4528 bytes copied

Process finished with exit code 0

```

## End Lab