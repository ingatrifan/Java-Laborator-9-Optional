package org.lab9.JDBC;

import org.lab9.entity.Album;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumDAO {
    public static void create(String name, int artistId, int releaseYear) {
        Database database = Database.getInstance();
        String queryString = "INSERT INTO albums(name,artist_id,release_year) VALUES('"+name+"',"+artistId+","+releaseYear+");";
        database.makeUpdate(queryString);
    }
    public static Album findByArtistId(int artistId){
        Database database = Database.getInstance();
        String queryString = "SELECT * FROM albums WHERE artist_id="+artistId;
        ResultSet rs =database.makeQuery(queryString);
        try {
            if (rs.next()){
                Album album = new Album();
                album.setId(rs.getInt(1));
                album.setName(rs.getString(2));
                album.setArtistId(rs.getInt(3));
                album.setReleaseYear(rs.getInt(4));
                return album;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Album findByRowNum(int rowNum){
        Database database = Database.getInstance();
        String queryString = "SELECT * FROM albums LIMIT "+rowNum+",1";
        ResultSet rs =database.makeQuery(queryString);
        try {
            if (rs.next()){
                Album album = new Album();
                album.setId(rs.getInt(1));
                album.setName(rs.getString(2));
                album.setArtistId(rs.getInt(3));
                album.setReleaseYear(rs.getInt(4));
                return album;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int count(){
        Database database = Database.getInstance();
        String queryString = "select count(*) from albums";
        ResultSet rs =database.makeQuery(queryString);
        try {
            if (rs.next())return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
