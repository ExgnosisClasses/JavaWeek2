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


class Person implements Serializable {

    @Serial
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
