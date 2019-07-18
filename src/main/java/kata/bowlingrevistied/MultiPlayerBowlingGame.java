package kata.bowlingrevistied;

import kata.bowling.BowlingGame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiPlayerBowlingGame {

   private final Map<String, BowlingGame> bowlingGames = new HashMap<>();

   public MultiPlayerBowlingGame(List<String> playerNames) {
      for (String playerName : playerNames) {
         bowlingGames.put(playerName, new BowlingGame());
      }
   }

   public void rollPins(int numberOfPins, String playerName) {
      if (!bowlingGames.containsKey(playerName)) {
         throw new InvalidPlayerException();
      }
      bowlingGames.get(playerName).score(numberOfPins);
   }

   public int getScore(String playerName) {
      if (!bowlingGames.containsKey(playerName)) {
         throw new InvalidPlayerException();
      }
      return bowlingGames.get(playerName).getScore();
   }
}
