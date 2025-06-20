# Lab 5-4 Serialization IO

## Lab Overview

In this lab, you will serialize and deserialize a Java object

## Part 1: Setup

- Start with a new Java project and a new package `serial`. 
- Create a `Runner` class with a `main()` method.

<br/>
<br/>

## Part 2: The Person class

- In the same file as the `Runner` class, create a class with `package` visibility called `Person` which implements the `Serializable` interface 
- Create three instance variables as shown in the code below
- The instance variable `id` is marked as `transient`, which means it will be ignored in the serialization process
- Define a static final long constant that represents the serialization `UUID``. It is important that this variable be named _EXACTLY_ as shown in the code below.
- The `toString()` method is included for convenience

```java
class Person implements Serializable {

	private static final long serialVersionUID = 8879L;

	private String name = null;
	private int age;
	private transient int id;

	public Person(String name, int age, int id) {
		super();
		this.name = name;
		this.age = age;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", id=" + id + "]";
	}

}

```


## Part 3: The Serialization code

- Create the object to be serialized, in this case `bob`.
- Also create another reference variable, in this case called `otherbob` which is initially set to `null`. This will hold the deserialized `Person` object.

```java
public class Runner {

	public static void main(String[] args) {

		Person bob = new Person("Bob", 34, 9989);
		Person otherBob = null;
		System.out.println(bob);
```

- Just like you did with the `BufferedWriter`, create a `FileOutputStream` object then wrap it in an `ObjectOutputStream`. 
- This wrapper will manage the serialization of the object. 
- It is convention to use the file extension `“.ser”` to indicate the file contains a serialized object.
- We have to cast `otherbob` to a `Person` object because Java doesn’t know what sort of object it is deserializating. All that it knows is that is some subclass of `Object`.
- The code is available in the file `serial.java` in the Solutions folder
- The final code for the `Runner` class should look like this


```java
mport java.io.*;

public class Runner {
    public static void main(String[] args) {

        Person bob = new Person("Bob", 34, 9989);
        Person otherBob = null;
        System.out.println(bob);

        try {
            // Serialize Bob into the file "person.ser"
            FileOutputStream outfile = new FileOutputStream("person.ser");
            ObjectOutputStream out = new ObjectOutputStream(outfile);
            out.writeObject(bob);
            out.close();
            outfile.close();

            // deserialize the contents of the file "person.ser" into otherbob
            FileInputStream infile = new FileInputStream("person.ser");
            ObjectInputStream in = new ObjectInputStream(infile);
            otherBob = (Person) in.readObject();
            in.close();
            infile.close();

            System.out.println(otherBob);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
```
<br/>
<br/>

## Part 4: Run the Serialization code

Run the code, and you should see that `bob` and `otherbob` are the same except for the `id` value. The `id` was marked as `transient` so it was never serialized. When `otherbob` was deserialized, since there was no provided value for `id`, that variable had the default value of zero.

```console
Person [name=Bob, age=34, id=9989]
Person [name=Bob, age=34, id=0]
```



## Part 5: Run the Serialization code

- Run the code to make sure that you have a copy of `person.ser` on disk.
- Comment out the code that serializes `bob`
- Change the value of `serialVersionUID` to any other value.
- Run the code to deserialze `otherbob`
- Because `person.ser` was created with a different version of `Person`, an exception will be thrown

```console

Person [name=Bob, age=34, id=9989]
java.io.InvalidClassException: serial.Person; local class incompatible: stream classdesc serialVersionUID = 8879, local class serialVersionUID = 1

```

## End Lab