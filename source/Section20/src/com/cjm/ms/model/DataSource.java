package com.cjm.ms.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static final String DB_NAME = "music.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:Data\\" + DB_NAME;

    private static final String TABLE_ARTISTS = "artists";
    private static final String COL_ARTIST_ID = "_id";
    private static final String COL_ARTIST_NAME = "name";
    private static final int INDEX_ARTIST_ID = 1;
    private static final int INDEX_ARTIST_NAME = 2;

    private static final String TABLE_ALBUMS = "albums";
    private static final String COL_ALBUM_ID = "_id";
    private static final String COL_ALBUM_NAME = "name";
    private static final String COL_ALBUM_ARTIST = "artist";
    private static final int INDEX_ALBUM_ID = 1;
    private static final int INDEX_ALBUM_NAME = 2;
    private static final int INDEX_ALBUM_ARTIST = 3;

    private static final String TABLE_SONGS = "songs";
    private static final String COL_SONG_ID = "_id";
    private static final String COL_SONG_TRACK = "track";
    private static final String COL_SONG_TITLE = "title";
    private static final String COL_SONG_ALBUM = "album";
    private static final int INDEX_SONG_ID = 1;
    private static final int INDEX_SONG_TRACK = 2;
    private static final int INDEX_SONG_TITLE = 3;
    private static final int INDEX_SONG_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    private Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
        } catch(SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
        }
        return conn != null;
    }

    public void close() {
        try {
            if(conn != null) {
                conn.close();
            }
        } catch(SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public List<Artist> queryArtist(int sortOrder) {
//        Statement statement = null;
//        ResultSet results = null;
//        // Messy way of dealing with resources.
//        try {
//            statement = conn.createStatement();
//            results = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS);
//
//            List<Artist> artists = new ArrayList<>();
//            while(results.next()) {
//                Artist artist = new Artist();
//                artist.setId(results.getInt(COL_ARTIST_ID));
//                artist.setName(results.getString(COL_ARTIST_NAME));
//                artists.add(artist);
//            }
//
//            return artists;
//        } catch(SQLException e) {
//            System.out.println("Query failed: " + e.getMessage());
//        } finally {
//            try {
//                if(results != null) {
//                    results.close();
//                }
//            } catch(SQLException e) {
//                System.out.println("Couldn't close results: " e.getMessage());
//            }
//            try {
//                if(statement != null) {
//                    statement.close();
//                }
//            } catch(SQLException e) {
//                System.out.println("Couldn't close statement: " e.getMessage());
//            }
//        }

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        if(sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COL_ARTIST_NAME);
            sb.append(" COLLATE NOCASE ");
            if(sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        // Try-with-resources statements makes it a lot clearer and automatically closes resources.
        // Resources implement AutoCloseable.
        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sb.toString())) {

            List<Artist> artists = new ArrayList<>();
            while(results.next()) {
                Artist artist = new Artist();
                artist.setId(results.getInt(INDEX_ARTIST_ID));
                artist.setName(results.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }

            return artists;
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }

        return null;
    }
}
