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
