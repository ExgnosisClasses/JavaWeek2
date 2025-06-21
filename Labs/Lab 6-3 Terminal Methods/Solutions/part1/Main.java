package part1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {

	public static void main(String[] args) {
		// Data
		List<Double> vals = Arrays.asList(4.35, 4.9, 123.0, 1.8);
		List<Integer> data = Arrays.asList(3,2,9,4,3,9,4,1,9,0,3,5,4,2,7,6);
		
		// use a reducer to sum up the doubles
		double sum = vals.stream()
				.reduce(0.0, (x,y) -> x + y);
		System.out.println(sum);
		
		// Use an optional to find the max value.
		Comparator<Integer> comparator = Comparator.comparing(Integer::intValue);
		Optional<Integer> maxOptional = data.stream()
				.max(comparator);
		maxOptional.ifPresent(e -> System.out.println("\nMax: " + e));
		
		// Determine if anything is divisible by 3 and less than 10
		boolean result;
		result = data.stream()
			.anyMatch(x -> 0 == x % 3);
        System.out.println("Any divisible by 3 is " + result);
        result = data.stream()
    			.allMatch((x) -> 10 >= x);
            System.out.println("All numbers less than 10 is " + result);
        
	}

}
