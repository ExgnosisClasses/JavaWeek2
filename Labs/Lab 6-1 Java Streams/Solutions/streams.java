package streams;

import java.util.Arrays;
import java.util.List;



public class Main {

    public static void main(String[] args) {

        List<String> randomValues = Arrays.asList(
                "E11", "D12", "A13", "F14", "C15",
                "A16", "B11", "B12", "C13", "B14",
                "B15", "B16", "F12", "E13", "C11",
                "C14", "A15", "C16", "F11", "C12",
                "D13", "E14", "D15", "D16");

        randomValues.stream()      					// Create the streams
                .filter(value->value.startsWith("C"))   	// Filter pipeline method
                .sorted()								 	// Sort pipeline method
                .forEach(System.out::println);	     		// Terminal forEach
        // .forEach(x -> System.out.println(x));
    }
}

