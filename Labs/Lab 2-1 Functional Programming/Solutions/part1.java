import java.time.LocalDate;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    // Define several static variables to hold different function types
    public static Function<Integer,Integer> square;
    public static Predicate<Integer> isEven;
    public static BiFunction<Integer,Integer,Integer> sum;
    public static Supplier<String> today;
    // This variable is initialized
    public static Consumer<String> printLength = (s) -> System.out.print(s.length());

    public static void main(String[] args) {
        // Assign the variables values in the form of Lambda expressions
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

