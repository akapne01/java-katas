package kata.mp3;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.contains;

public class MP3PlayerTest {

   @Test
   public void shouldReturnSongWhenSearched() {
      // Given
      MP3Player mp3Player = new MP3Player();
      Song song = new Song("Random", "Randomizer");
      mp3Player.addSong(song);

      // When
      Song returnedSong = mp3Player.findByTitle("Random").get(0);

      // Then
      assertThat(returnedSong, is(song));

   }

   @Test
   public void shouldReturnSongWhenPartialSearch() {
      // Given
      MP3Player mp3Player = new MP3Player();
      Song song1 = new Song("Random", "Randomizer");
      Song song2 = new Song("Run ran", "Randomizer");
      Song song3 = new Song("Sun", "Randomizer");
      mp3Player.addSong(song1);
      mp3Player.addSong(song2);
      mp3Player.addSong(song3);

      // When
      List<Song> returedList = mp3Player.findByTitle("ran");

      // Then
      assertThat(returedList.size(), is(2));
      assertTrue(returedList.contains(song1));
      assertTrue(returedList.contains(song2));
   }
}