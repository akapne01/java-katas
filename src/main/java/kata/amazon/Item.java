package kata.amazon;

public class Item {
    private final double price;
    private final String description;

    public Item(double price, String description){
        this.price = price;
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

}
