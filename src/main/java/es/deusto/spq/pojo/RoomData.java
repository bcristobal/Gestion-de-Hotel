package es.deusto.spq.pojo;

public class RoomData {

	private int number ;
	private int capacity;  
    private String type;
    private double price;
    private String description;
	
    public RoomData() {
    	
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RoomData [number=" + number + ", type=" + type + ", price=" + price + "]";
	}
    
    
    
}
