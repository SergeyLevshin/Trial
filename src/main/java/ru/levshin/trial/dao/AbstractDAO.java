package ru.levshin.trial.dao;

import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class AbstractDAO<T> {

    @Setter
    private Class<T> clazz;

    @PersistenceContext
    protected EntityManager entityManager;

    public T findById(final long id) {
        return getEntityManager().find(clazz, id);
    }

    public List<T> findAll() {
        return getEntityManager().createQuery("SELECT c FROM " + clazz.getSimpleName() + " c", clazz).getResultList();
//        return getCurrentSession().createQuery("from " + clazz.getName()).getResultList();
    }

    public T create(final T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    public T update(final T entity) {
        return (T) getEntityManager().merge(entity);
    }

    public void delete(final T entity) {
        getEntityManager().remove(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findById(entityId);
        delete(entity);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
