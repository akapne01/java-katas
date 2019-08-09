package kata.mp3;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

   @Test
   public void shouldReturnHowManySongsPerArtist() {
      // given
      MP3Player mp3Player = new MP3Player();
      Song song1 = new Song("Summer", "Seasons");
      Song song2 = new Song("Winter", "Seasons");
      Song song3 = new Song("Spring", "Boot");
      mp3Player.addSong(song1);
      mp3Player.addSong(song2);
      mp3Player.addSong(song3);



      // when
      Map<String, Integer> songsPerArtist  = mp3Player.songsPerArtist();

      // then
      assertThat(songsPerArtist.size(), is(2));
      assertThat(songsPerArtist.get("Seasons"), is(2));
      assertThat(songsPerArtist.get("Boot"), is(1));
   }

   @Test
   public void qhwe() {
      MP3Player mp3Player = new MP3Player();
      mp3Player.addSong(new Song("Should I stay or should I go", "The Clash"));

      List<Song> songs = mp3Player.songList();

      for (Song song : songs) {
         System.out.println(song);
      }

      songs.add(new Song("Sunny day","The temptations"));

      List<Song> songsAgain = mp3Player.songList();

      for (Song song : songsAgain) {
         System.out.println(song);
      }
   }
}