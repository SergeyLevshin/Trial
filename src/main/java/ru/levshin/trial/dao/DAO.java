package ru.levshin.trial.dao;

public interface DAO<T> {

    void setClazz(Class< T > clazzToSet);

    T findById(final long id);

    Iterable<T> findAll();

    T create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);
}
