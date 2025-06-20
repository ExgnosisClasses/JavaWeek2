package part2;

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
				//.skip(10)
				//.skip(100)
				//.limit(10)
				//.distinct()
				//.limit(10)
				.collect(Collectors.toList());
		
		System.out.println(results);
	}

}
