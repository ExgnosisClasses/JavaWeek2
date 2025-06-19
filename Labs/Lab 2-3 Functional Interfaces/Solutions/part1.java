package funct3;

public class Main {

    public static void main(String[] args) {

    }
}

@FunctionalInterface
interface Func {
    void exec();
    // note that adding the following method produces a compile error since
    // we no longer have exactly one abstract method
    // void show();
}