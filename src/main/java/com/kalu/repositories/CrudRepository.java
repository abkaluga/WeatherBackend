package com.kalu.repositories;

import com.kalu.models.db.DBObject;

import java.util.Set;

/**
 * Created by Albert on 13.01.2016.
 */
public interface CrudRepository<T extends DBObject> {

    public T add(T t);
    public T edit(T t);
    public T get(Long k);
    public T delete(T t);
    public T delete(Long k);

    public Set<T> getAll();

}
