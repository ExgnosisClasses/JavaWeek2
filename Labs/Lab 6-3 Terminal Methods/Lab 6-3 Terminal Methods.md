# Lab 6-3: Terminal Methods

## Lab Objectives

The purpose of this lab is to get you familiar with some of the terminal methods 


## Lab Setup

- You can do this whole lab in one project with different packages for the different parts
- Solutions for each part are provided in the Solutions directory. 
- These are not the only possible solutions, and your solution to the labs may be a different in some ways than the reference solutions.
- To make the lab more challenging, the requirements are going to be stated, and you will be responsible for coming up with a solution. There is a solution for each part provided, but these are not necessarily the only solutions

## Part 1: Reducers

- Start with a list of Doubles as shown

```java
List<Double> vals = Arrays.asList(4.35, 4.9, 123.0, 1.8);
```

- Using the `reduce()` terminal method, calculate the sum of the numbers.


- With the list of Integers, shown below, find the maximum value.  

```java
List<Integer> data = Arrays.asList(3,2,9,4,3,9,4,1,9,0,3,5,4,2,7,6);
```

- Remember that you have to you use an optional as shown here. Consult the solution code if you need some guidance.
- Using allMatch() and anyMatch(), determine if there are any numbers that are divisible by 3 and if all the numbers are less that 10. 
- Kind of trivial, but it’s about getting you used to writing the code.
- Consult the solution in the `part1` directory if you need a hint.

## Part 2: Collectors

- Create a class called `StockItem` as shown below

```java
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
```

- Create some dummy data making sure that all the stock numbers are distinct, such as below:

```java
List<StockItem> inventory = Arrays.asList(
			new StockItem(1111,"Hi Resolution Monitor"),
			new StockItem(2222, "Ergonomic Mouse"),
			new StockItem(3333, "GPU Card"));
```

- Use a collector to create a map where the key value is the stockNumber and the value is the description. 
- Your output should look like this

```console
{3333=GPU Card, 1111=Hi Resolution Monitor, 2222=Ergonomic Mouse}

Process finished with exit code 0
```

- Once your code is running, add a duplicate item as shown below and rerun the code. Why doesn’t it work?
- Remember that this is a collector that is only creating the map


```java
List<StockItem> inventory = Arrays.asList(
			new StockItem(1111,"Hi Resolution Monitor"),
			new StockItem(2222, "Ergonomic Mouse"),
            new StockItem(2222, "Standard Mouse"),
			new StockItem(3333, "GPU Card"));
```

```console
Exception in thread "main" java.lang.IllegalStateException: Duplicate key 2222 (attempted merging values Ergonomic Mouse and Standard Mouse)
	at java.base/java.util.stream.Collectors.duplicateKeyException(Collectors.java:135)
	at java.base/java.util.stream.Collectors.lambda$uniqKeysMapAccumulator$0(Collectors.java:182)
	

Process finished with exit code 1
```

- Since you don’t know in advance if any of the stock numbers are duplicated, what’s an alternative approach? 
- Why won’t filter() work? 
- What is the real challenge of implementing any approach that requires we to filter out duplicate objects based on some value? (Hint: How would we remember what objects we have already seen to know if we are processing a duplicate?)


## Part 3: Data transformations

- Using the same data from the previous section, convert the stream of objects into a stream of strings in a Json format. 
- This transformation should do this mapping

```text
StockItem(3333, “GPU Card) → “{‘stockNumber’ : ‘3333’, ‘description’ : ‘GPU Card’} “
```

- The way to approach this problem is by having each StockItem object be responsible for converting itself into JSon with a method like this:

```java
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
    // ---- Added method
	public String toJson() {
		String json = "{'stockNumber' : '"+ this.stockNumber + "',";
		json = json + "'description : '" + this.description + "' }";
		return json;
	}
	
}

```

- This is better program design than trying to encode the logic for converting a StockItem into the pipeline.
  - One main reason is that if how the transformation is done is changed and the logic is in the pipeline methods, the pipeline will break
  - If the pipeline just calls the `toJson()` method, then it won't break if the method is altered.

- We would do the transformation like this, using the method reference we saw earlier

```java
List<String> jsonInventory =
				inventory.stream()
				.map(StockItem::toJson)
				.collect(Collectors.toList());
```

## End Lab