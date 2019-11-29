package com.agawrysiuk.huntbeginsspringboot.data;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public final class Database {

    private static final String TILES_PATH = "classpath:static/game/tiles.txt";
    private static final String SMALL_TILES_PATH = "classpath:static/game/small_tiles.txt";

    private Database() {
    }

    public static Database getDatabase() {
        return new Database();
    }

    public BufferedReader getData() throws FileNotFoundException {
        return
                new BufferedReader(
                new FileReader(
                ResourceUtils.getFile(
                        TILES_PATH)));
    }
}
