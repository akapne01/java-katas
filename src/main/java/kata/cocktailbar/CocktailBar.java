package kata.cocktailbar;

import java.util.List;

public class CocktailBar {

    private final Clock clock;

    public CocktailBar(Clock clock) {
        this.clock = clock;
    }

    public int serve(List<Cocktail> orderedCocktails) {
        int sum = 0;
        int countOldFashioned = 0;

        if (orderedCocktails.size() > 5) {
            throw new ShouldntGetDrunkException();
        }

        for (Cocktail orderedCocktail : orderedCocktails) {
            if (orderedCocktail.getDescription().equals("Old Fashioned") && ++countOldFashioned % 2 == 0) {
                continue;
            }
            sum += orderedCocktail.getPrice();
        }
        if (clock.now().getHour() == 21) {
            sum /= 2;
        }

        return sum;
    }
}
