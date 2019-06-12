package kata.cocktailbar;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CocktailBarTest {


    private Clock clock = mock(Clock.class);
    private CocktailBar cocktailBar = new CocktailBar(clock);

    @Before
    public void setUp() {
        when(clock.now()).thenReturn(LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
    }


    @Test
    public void shouldReturnCostAsSumOfCocktailPrices() {
        List<Cocktail> cocktails = new ArrayList<>();
        cocktails.add(Cocktail.mojito());
        cocktails.add(Cocktail.godfather());
        assertThat(cocktailBar.serve(cocktails), is(19));

    }

    @Test
    public void playWithMocks() {
        when(clock.now()).thenReturn(LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));
        System.out.println(clock.now());
    }

    @Test
    public void shouldHave2for1ForOldFashioned() {
        List<Cocktail> cocktails = new ArrayList<>();
        cocktails.add(Cocktail.oldFashioned());
        cocktails.add(Cocktail.oldFashioned());
        assertThat(cocktailBar.serve(cocktails), is(11));
    }

    @Test
    public void shouldChargeHalfPriceDuringHappyHour() {
        when(clock.now()).thenReturn(LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 30)));
        List<Cocktail> cocktails = new ArrayList<>();
        cocktails.add(Cocktail.mojito());
        cocktails.add(Cocktail.mojito());
        assertThat(cocktailBar.serve(cocktails), is(10));
    }

    @Test
    public void shouldThrowExceptionIfOrderMoreThan5(){
        ArrayList<Cocktail> cocktails = new ArrayList<>();
        cocktails.add(Cocktail.mojito());
        cocktails.add(Cocktail.mojito());
        cocktails.add(Cocktail.mojito());
        cocktails.add(Cocktail.mojito());
        cocktails.add(Cocktail.mojito());
        cocktails.add(Cocktail.mojito());
        try {
            cocktailBar.serve(cocktails);
            fail();
        } catch (ShouldntGetDrunkException e) {

        }
    }
}