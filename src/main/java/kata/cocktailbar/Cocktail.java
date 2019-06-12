package kata.cocktailbar;

public class Cocktail {
    private final String description;
    private final int price;

    private Cocktail(String description, int price) {

        this.description = description;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public static Cocktail mojito() {
        return new Cocktail("mojito", 10);
    }

    public static Cocktail godfather() {
        return new Cocktail("godfather", 9);
    }

    public static Cocktail bloodyMary() {
        return  new Cocktail("Bloody Mary", 12);
    }

    public static Cocktail oldFashioned() {
        return new Cocktail("Old Fashioned", 11);
    }
}
