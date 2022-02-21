package com.ibrahim.songcache;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SongCacheTest {

    @Test
    void getPlaysForSongTest() {
        SongCache cache = new SongCacheImpl();
        cache.recordSongPlays("one", 10);
        cache.recordSongPlays("two", 15);
        cache.recordSongPlays("three", 20);
        cache.recordSongPlays("one", 10);

        assertEquals(20, cache.getPlaysForSong("one"));
        assertEquals(15, cache.getPlaysForSong("two"));
        assertEquals(20, cache.getPlaysForSong("three"));
        assertEquals(-1, cache.getPlaysForSong("invalid song id"));
    }

    @Test
    void getTopNSongsTest() {
        SongCache cache = new SongCacheImpl();
        cache.recordSongPlays("one", 10);
        cache.recordSongPlays("two", 20);
        cache.recordSongPlays("three", 30);
        cache.recordSongPlays("four", 40);
        cache.recordSongPlays("five", 10);

        assertEquals(new ArrayList<String>(), cache.getTopNSongsPlayed(0));
        assertEquals(List.of("four"), cache.getTopNSongsPlayed(1));
        assertEquals(List.of("four", "three"), cache.getTopNSongsPlayed(2));
        assertEquals(List.of("four", "three", "two"), cache.getTopNSongsPlayed(3));
        assertEquals(List.of("four", "three", "two", "one"), cache.getTopNSongsPlayed(4));
        assertEquals(List.of("four", "three", "two", "one", "five"), cache.getTopNSongsPlayed(5));
    }
}
