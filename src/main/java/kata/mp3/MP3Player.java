package kata.mp3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MP3Player {
   private final List<Song> songList = new ArrayList<>();

   public void addSong(Song song) {
      songList.add(song);
   }

   public List<Song> findByTitle(String songTitle) {

      return filterWithPredicate(Song::getTitle, songTitle);

//      List<Song> matchingSongs = new ArrayList<>();
//
//      for (Song song : songList) {
//         if (song.getTitle().toLowerCase().contains(songTitle.toLowerCase())) {
//            matchingSongs.add(song);
//         }
//      }
//
//      return matchingSongs;
   }

   public List<Song> findByArtist(String artistNme) {
      return filterWithPredicate(Song::getArtist, artistNme);

//      List<Song> matchingSongs = new ArrayList<>();
//
//      for (Song song : songList) {
//         if (song.getArtist().toLowerCase().contains(artistNme.toLowerCase())) {
//            matchingSongs.add(song);
//         }
//      }
//
//      return matchingSongs;
   }

   private List<Song> filterWithPredicate(Function<Song, String> function, String searchTerm) {
      return songList.stream()
            .filter(song -> function.apply(song).toLowerCase().contains(searchTerm.toLowerCase()))
            .collect(Collectors.toList());
   }
}
