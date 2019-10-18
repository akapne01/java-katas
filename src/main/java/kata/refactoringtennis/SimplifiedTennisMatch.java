package kata.refactoringtennis;

public class SimplifiedTennisMatch {

   private final String player1;
   private final String player2;
   private Score score = null;

   private int player1Points = 0;
   private int player2Points = 0;

   private final int MINIMUM_MARGIN_TO_WIN = 3;

   public SimplifiedTennisMatch(String player1, String player2) {
      this.player1 = player1;
      this.player2 = player2;
      this.score = new Score(player1, player2, 0, 0, null, false, false, false);
   }

   public void score(String playerName) {
      if ((player1Points >= 4 && player1Points - player2Points >= MINIMUM_MARGIN_TO_WIN) || (player2Points >= 4 && player2Points - player1Points >= MINIMUM_MARGIN_TO_WIN)) {
         throw new UnsupportedOperationException();
      }
      if (playerName.equals(player1)) {
         player1Points++;
      } else if (playerName.equals(player2)) {
         player2Points++;
      }

      String winner = null;
      boolean isDeuce = false;
      boolean isPlayer1Advantage = false;
      boolean isPlayer2Advantage = false;
      if (player1Points >= 4 && player1Points - player2Points >= MINIMUM_MARGIN_TO_WIN) {
         winner = player1;
      } else if (player2Points >= 4 && player2Points - player1Points >= MINIMUM_MARGIN_TO_WIN) {
         winner = player2;
      } else if (player1Points >= 4 && player1Points >= player2Points) {
         if (player1Points > player2Points) {
            isPlayer1Advantage = true;
         } else {
            isDeuce = true;
         }
      } else if (player2Points >= 4) {
         if (player2Points > player1Points) {
            isPlayer2Advantage = true;
         } else {
            isDeuce = true;
         }
      }

      score = new Score(player1, player2, player1Points, player2Points, winner, isDeuce, isPlayer1Advantage, isPlayer2Advantage);
   }

   public String formatScore() {
      if (player1Points >= 4 && player1Points - player2Points >= MINIMUM_MARGIN_TO_WIN) {
         return player1 + " wins!";
      } else if (player2Points >= 4 && player2Points - player1Points >= MINIMUM_MARGIN_TO_WIN) {
         return player2 + " wins!";
      }

   return new StringBuilder()
      .append(player1 + " - " + player2 + "\n")
      .append(formatMatchScores())
      .toString();
}

   private String formatMatchScores() {
      if (player1Points >= 4) {
         return player1Points > player2Points ? "ADVANTAGE - " : "DEUCE";
      } else if (player2Points >= 4) {
         return player2Points > player1Points ? " - ADVANTAGE" : "DEUCE";
      }

      return formatPoints(player1Points) + " - " + formatPoints(player2Points);
   }

   public Score getScore() {
      return score;
   }

   public String formatScoreModern(Score score) {
      /**
       * Part 4 - change null for appropriate Score object
       */

      String player1ScoreString = "  ";
      String player2ScoreString = "  ";
      if (score.getWinner().isPresent() && score.getWinner().get().equals(score.getPlayer1())) {
         player1ScoreString = "WI";
      } else if (score.getWinner().isPresent() && score.getWinner().get().equals(score.getPlayer2())) {
         player2ScoreString = "WI";
      } else if (score.isPlayer1Advantage()) {
         player1ScoreString = "AD";
      } else if (score.isPlayer2Advantage()) {
         player2ScoreString = "AD";
      } else if (score.isDeuce()) {
         player1ScoreString = "DC";
         player2ScoreString = "DC";
      } else {
         player1ScoreString = formatPoints(score.getPlayer1Points());
         player2ScoreString = formatPoints(score.getPlayer2Points());
      }

      return new StringBuilder()
         .append("+-----+------+\n")
         .append("| ")
         .append(score.getPlayer1().substring(0, Math.min(score.getPlayer1().length(), 3)).toUpperCase())
         .append(" |  ")
         .append(player1ScoreString)
         .append("  |\n")
         .append("+-----+------+\n")
         .append("| ")
         .append(score.getPlayer2().substring(0, Math.min(score.getPlayer2().length(), 3)).toUpperCase())
         .append(" |  ")
         .append(player2ScoreString)
         .append("  |\n")
         .append("+-----+------+\n")
         .toString();
   }

   private String formatPoints(int points) {
      switch (points) {
         case 0:
            return "0";
         case 1:
            return "15";
         case 2:
            return "30";
         case 3:
            return "40";
         default:
            return Integer.toString(points);
      }
   }
}
