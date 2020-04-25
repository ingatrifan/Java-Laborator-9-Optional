package org.lab9.app;


import org.lab9.JDBC.ArtistDAO;
import org.lab9.entity.Album;
import org.lab9.entity.Artist;
import org.lab9.repo.AlbumRepository;
import org.lab9.repo.ArtistRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static void testJPA(){
        ArtistRepository artistRepository = new ArtistRepository();
        Artist artist = new Artist();
        artist.setName("artist1");
        artist.setCountry("country1");
        artistRepository.create(artist);
        Artist artist1 = new Artist();
        artist1.setName("artist2");
        artist1.setCountry("country2");
        artistRepository.create(artist1);
        AlbumRepository albumRepository = new AlbumRepository();
        Album album = new Album();
        album.setName("album1");
        album.setArtistId(50);
        album.setReleaseYear(2000);
        albumRepository.create(album);
        Album album1 = new Album();
        album1.setName("album2");
        album1.setArtistId(50);
        album1.setReleaseYear(2010);
        albumRepository.create(album1);
        Artist artist2 = artistRepository.findById(50,Artist.class);
        System.out.println(artist2.getCountry());
        List<Album> albumList = albumRepository.findByArtistId(50);
        for(Album album2 : albumList){
            System.out.println(album2.getName());
        }
    }
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the method: JDBC or JPA: \n");
        String method = scanner.next();
        if (method.equals("JPA")) testJPA(); else
            if (method.equals("JDBC")) testJDBC();
    }
    public static void testJDBC(){
        Artist artist;
        artist = ArtistDAO.findByName("artist1");
        System.out.println(artist.getName()+" "+artist.getCountry());
    }
}
