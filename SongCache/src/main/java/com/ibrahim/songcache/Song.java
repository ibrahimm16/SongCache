package com.ibrahim.songcache;

import lombok.Data;

@Data
public class Song {

    private String songId;
    private Integer numPlays = 0;
}
