# SongCache
Classes
  1. Song
      - String songId
      - Integer numPlays
  2. SongCache
      - Interface containing the functions recordSongPlays, getPlaysForSong, and getTopNSongsPlayed
  3. SongCacheImpl
      - Class implementation of SongCache. Uses CompletableFuture and a cached thread pool to achieve concurrency safely.
  4. SongCacheTest
      - JUnit test class that tests functionallity for the three SongCache functions (recordSongPlays, getPlaysForSong, getTopNSongsPlayed)
