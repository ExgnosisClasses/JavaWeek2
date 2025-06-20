package part1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		List<Integer> data = Arrays.asList(
				8, 9, 4, 3, 1, 
				9, 1, 2, 9, 8, 
				5, 3, 4, 10, 6,
				5, 7, 5, 1, 6);
		
		List<Integer> results =
				data.stream()
				.peek(x -> System.out.println("First peek " + x))
				.filter(x -> x > 4)
				.peek(x -> System.out.println("Second peek " + x))
				.map(x -> (x == 9) ? 0 : x)
				.sorted()
				.collect(Collectors.toList());
		/*
		List<Integer> results =
				data.stream()
				//.peek(x -> System.out.println("First peek " + x))
				.map(x -> (x == 9) ? 0 : x)
				.sorted()
				.peek(x -> System.out.println("Second peek " + x))
				.filter(x -> x > 4)
				.collect(Collectors.toList());
		*/
		
		System.out.println(results);
	}

}
