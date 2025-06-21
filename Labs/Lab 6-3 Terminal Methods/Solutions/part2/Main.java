package part2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    
	public static void main(String[] args) {
		
	List<StockItem> inventory = Arrays.asList(
			new StockItem(1111,"Hi Resolution Monitor"),
			new StockItem(2222, "Ergonomic Mouse"),
			new StockItem(3333, "GPU Card"));
	
	Map<Integer,String> stockList =
			inventory.stream()
			.collect(Collectors.toMap(StockItem::getStockNumber, StockItem::getDescription));
	System.out.println(stockList);
	
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
	
}