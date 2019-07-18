package kata.bowlingrevistied;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MultiPlayerBowlingGameTest {

   MultiPlayerBowlingGame multiPlayerBowlingGame;

   @Before
   public void setUp() {
      List<String> playerNames = new ArrayList<>();
      playerNames.add("Eduardo");
      playerNames.add("Agnese");
      multiPlayerBowlingGame = new MultiPlayerBowlingGame(playerNames);
   }

   @Test
   public void shouldReturnScoreForMultiplePlayers() {
      multiPlayerBowlingGame.rollPins(10, "Eduardo");
      multiPlayerBowlingGame.rollPins(9, "Agnese");

      assertThat(multiPlayerBowlingGame.getScore("Eduardo"), is(10));
      assertThat(multiPlayerBowlingGame.getScore("Agnese"), is(9));
   }

   // Expect exception to be thrown
   @Test(expected = InvalidPlayerException.class)
   public void shouldThrowExceptionIfPlayerIsNotInTheGame() {
      multiPlayerBowlingGame.rollPins(9, "Someone Else");
   }

   @Test(expected = InvalidPlayerException.class)
   public void shouldThrowExceptionObtainingScoreForPlayerNotInGame() {
      multiPlayerBowlingGame.getScore("Someone Else");
   }

   @Test
   public void name() {
      Map<String, String> map = new HashMap<>();
      map.put("hi", "ho");

      String string = map.get("hi");
      string.substring(0, 3);
   }


}