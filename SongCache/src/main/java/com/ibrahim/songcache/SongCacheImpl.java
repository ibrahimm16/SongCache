package com.ibrahim.songcache;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SongCacheImpl implements SongCache {

    private final Map<String, Song> songs;
    private final ExecutorService executorService;

    public SongCacheImpl() {
        songs = new ConcurrentHashMap<>();
        executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void recordSongPlays(String songId, int numPlays) {
        CompletableFuture.runAsync(() -> {
            Song song = songs.getOrDefault(songId, new Song());
            song.setSongId(songId);
            song.setNumPlays(song.getNumPlays() + numPlays);
            songs.put(songId, song);
        }, executorService).join();
    }

    @Override
    public int getPlaysForSong(String songId) {
        CompletableFuture<Integer> songPlaysFuture = CompletableFuture.supplyAsync(() -> {
            Song song = songs.get(songId);
            if (song == null) return -1;
            else return song.getNumPlays();
        }, executorService);

        return songPlaysFuture.join();
    }

    @Override
    public List<String> getTopNSongsPlayed(int n) {
        CompletableFuture<List<String>> topNSongsFuture = CompletableFuture.supplyAsync(() -> {
            List<Song> songList = new ArrayList<>(songs.values().stream().toList());
            songList = new ArrayList<>(songList.stream().sorted(Comparator.comparing(Song::getNumPlays).reversed()).limit(n).toList());
            List<String> topNSongs = new ArrayList<>();
            songList.forEach((song) -> topNSongs.add(song.getSongId()));
            return topNSongs;
        }, executorService);

        return topNSongsFuture.join();
    }
}
