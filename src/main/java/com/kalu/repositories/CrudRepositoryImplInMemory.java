package com.kalu.repositories;

import com.kalu.models.db.DBObject;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Albert on 13.01.2016.
 */
public class CrudRepositoryImplInMemory<T extends DBObject> implements CrudRepository<T> {
    AtomicLong next = new AtomicLong(0L);
    Map<Long, T> db = new ConcurrentSkipListMap<Long, T>();

    public T add(T t){
        if (!db.containsKey(t.getId())){
            db.remove(t.getId());
            db.put(t.getId(), t);
            return t;
        } else {
            return null;
        }

    }
    public T edit(T t){
        if (db.containsKey(t.getId())){
            db.remove(t.getId());
            db.put(t.getId(), t);
            return t;
        } else {
            return null;
        }
    }
    public T get(Long k){
        return db.get(k);
    }
    public T delete(T t){
        return delete(t.getId());
    }
    public T delete(Long k){
        return db.remove(k);
    }

    public Set<T> getAll(){
        Set<T> set = Collections.synchronizedSet(new HashSet<T>(db.values()));
        return set;

    }
}
