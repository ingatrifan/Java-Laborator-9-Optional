package org.lab9.JDBC;

import org.lab9.entity.Artist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {
    public static void create(String name, String country){
        Database database = Database.getInstance();
        String queryString = "INSERT INTO artists(name,country) VALUES('"+name+"','"+country+"');";
        database.makeUpdate(queryString);
    }
    public static int count(){
        Database database = Database.getInstance();
        String queryString = "select count(*) from artists";
        ResultSet rs =database.makeQuery(queryString);
        try {
            if (rs.next())return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static Artist findByName(String name){
        Database database = Database.getInstance();
        String queryString = "SELECT * FROM artists WHERE name='"+name+"';";
        ResultSet rs =database.makeQuery(queryString);
        try {
            if (rs.next()){
                Artist artist = new Artist();
                artist.setId(rs.getInt(1));
                artist.setName(rs.getString(2));
                artist.setCountry(rs.getString(3));
                return artist;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static Artist findByRowNum(int rowNum){
        Database database = Database.getInstance();
        String queryString = "SELECT * FROM artists LIMIT "+rowNum+",1";
        ResultSet rs =database.makeQuery(queryString);
        try {
            if (rs.next()){
                Artist artist = new Artist();
                artist.setId(rs.getInt(1));
                artist.setName(rs.getString(2));
                artist.setCountry(rs.getString(3));
                return artist;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static List<Artist> getArtistsByChart(){
        List<Artist> artists = new ArrayList<>();
        Database database = Database.getInstance();
        String queryString = "SELECT a.id,a.name,a.country FROM artists a " +
                "join albums alb on alb.artist_id = a.id " +
                "join chart c on alb.id = c.album_id  " +
                "order by c.position";
        ResultSet rs =database.makeQuery(queryString);
        try {
            while (rs.next()){
                Artist artist = new Artist();
                artist.setId(rs.getInt(1));
                artist.setName(rs.getString(2));
                artist.setCountry(rs.getString(3));
                artists.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }
}
