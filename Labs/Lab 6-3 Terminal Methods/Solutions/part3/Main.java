package part3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;





public class Main {

	public static void main(String[] args) {
		List<StockItem> inventory = Arrays.asList(
				new StockItem(1111,"Hi Resolution Monitor"),
				new StockItem(2222, "Ergonomic Mouse"),
				new StockItem(2222, "Standard Mouse"),
				new StockItem(3333, "GPU Card"));
		
		List<String> jsonInventory =
				inventory.stream()
				.map(StockItem::toJson)
				.collect(Collectors.toList());
                
	
	   jsonInventory.forEach(System.out::println);

}
}

class StockItem {
	private int stockNumber;
	private String description;

	public StockItem(int stockNumber, String description) {
		this.stockNumber = stockNumber;
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public int getStockNumber() {
		return stockNumber;
	}
	public String toJson() {
		String json = "{'stockNumber' : '"+ this.stockNumber + "',";
		json = json + "'description : '" + this.description + "' }";
		return json;
	}
	
}