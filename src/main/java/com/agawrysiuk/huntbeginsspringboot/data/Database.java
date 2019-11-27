package com.agawrysiuk.huntbeginsspringboot.data;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public final class Database {

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
                "classpath:static/game/tiles.txt")));
    }
}
