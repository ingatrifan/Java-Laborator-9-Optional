package org.lab9.repo;

import org.lab9.entity.Album;
import org.lab9.util.PersistenceUtil;

import javax.persistence.EntityManager;

public interface AbstractRepository<T> {
    default public void create(T t){
        PersistenceUtil persistenceUtil = PersistenceUtil.getPersistenceUtil();
        EntityManager entityManager = persistenceUtil.createEMFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    default public T findById(int id,Class<T> genericClass){
        PersistenceUtil persistenceUtil = PersistenceUtil.getPersistenceUtil();
        EntityManager entityManager = persistenceUtil.createEMFactory().createEntityManager();
        T t = entityManager.find(genericClass,id);
        entityManager.close();
        return  t;
    }
}
