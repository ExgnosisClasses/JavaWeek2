package part3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {

    List<String> asia = Arrays.asList("Japan","Korea", "China", "Laos");
    List<String> europe = Arrays.asList("England","France", "Sweden", "Laos");
    List<String> sa = Arrays.asList("Brazil","Argentia", "Peru", "Guyana");
    List<String> fifa =  Arrays.asList("Brazil","Argentia", "England", "France"); 
    List<String> nato =  Arrays.asList("Sweden", "England", "France");
    
    
    List< List<String> > countries = new ArrayList< List<String> >();
    countries.add(europe);
    countries.add(asia);
    countries.add(sa);
    countries.add(fifa);
    countries.add(nato);
   
    System.out.println(countries);
    
    List<String> nations =
    		countries.stream()
    		.flatMap(x -> x.stream())
    		//.distinct()
    		//.sorted()
    		.collect(Collectors.toList());
    
    System.out.println(nations);
  
	}
    
	
}
