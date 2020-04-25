package org.lab9.repo;


import org.lab9.entity.Album;
import org.lab9.entity.Artist;
import org.lab9.util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AlbumRepository implements AbstractRepository<Album> {
    public List<Album> findByName(String name){
        PersistenceUtil persistenceUtil = PersistenceUtil.getPersistenceUtil();
        EntityManager entityManager = persistenceUtil.createEMFactory().createEntityManager();
        TypedQuery<Album> query = entityManager.createNamedQuery("Album.findByName",Album.class)
                .setParameter("name",name);
        List<Album> albums = query.getResultList();
        entityManager.close();
        return albums;
    }
    public List<Album> findByArtistId(int artistId){
        PersistenceUtil persistenceUtil = PersistenceUtil.getPersistenceUtil();
        EntityManager entityManager = persistenceUtil.createEMFactory().createEntityManager();
        TypedQuery<Album> query = entityManager.createNamedQuery("Album.findAlbumByArtistId",Album.class)
                .setParameter("artistId",artistId);
        List<Album> albums = query.getResultList();
        entityManager.close();
        return albums;
    }
}
