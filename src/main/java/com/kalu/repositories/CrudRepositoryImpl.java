package com.kalu.repositories;

import com.kalu.config.Database;
import com.kalu.models.db.DBObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.*;

/**
 * Created by Albert on 14.01.2016.
 */
public abstract class CrudRepositoryImpl<T extends DBObject> implements CrudRepository<T> {



    protected abstract Class<T> getInnerClass();


    public T add(T t){
        Optional<Session> s = Optional.empty();
        Optional<Transaction> tx = Optional.empty();

        try {

            s = Optional.ofNullable(Database.getSession());
            if (s.isPresent()){
                tx = Optional.ofNullable(s.get().getTransaction());
                if (tx.isPresent()){
                    tx.get().begin();
                    s.get().persist(t);
                    tx.get().commit();
                }
            }

        } catch (Exception e){
            if (tx.isPresent()){
                tx.get().rollback();
            }
            return null;
        } finally {
            if (s.isPresent()){
                s.get().close();
            }
        }


    return t;
    }
    public T edit(T t){
        Optional<Session> s = Optional.empty();
        Optional<Transaction> tx = Optional.empty();

        try {

            s = Optional.ofNullable(Database.getSession());
            if (s.isPresent()){
                tx = Optional.ofNullable(s.get().getTransaction());
                if (tx.isPresent()){
                    tx.get().begin();
                    s.get().merge(t);
                    tx.get().commit();
                }
            }

        } catch (Exception e){
            if (tx.isPresent()){
                tx.get().rollback();
            }
            return null;
        } finally {
            if (s.isPresent()){
                s.get().close();
            }
        }


        return t;
    }
    public T get(Long k){
        Optional<Session> s = Optional.empty();
        Optional<Transaction> tx = Optional.empty();
        T t = null;
        try {

            s = Optional.ofNullable(Database.getSession());
            if (s.isPresent()){
                tx = Optional.ofNullable(s.get().getTransaction());
                if (tx.isPresent()){
                    tx.get().begin();
                    t = (T) s.get().get(getInnerClass(),k);
                    initObject(t);
                    tx.get().commit();
                }
            }

        } catch (Exception e){
            if (tx.isPresent()){
                tx.get().rollback();
            }
            return null;
        } finally {
            if (s.isPresent()){
                s.get().close();
            }
        }
        return t;
    }

    protected abstract void initObject(T t);

    public T delete(T t){
        return delete(t.getId());
    }
    public T delete(Long k){
        Optional<Session> s = Optional.empty();
        Optional<Transaction> tx = Optional.empty();
        T t = null;
        try {

            s = Optional.ofNullable(Database.getSession());
            if (s.isPresent()){
                tx = Optional.ofNullable(s.get().getTransaction());
                if (tx.isPresent()){
                    tx.get().begin();
                    t = (T) s.get().get(getInnerClass(),k);
                    s.get().delete(t);
                    tx.get().commit();
                }
            }

        } catch (Exception e){
            if (tx.isPresent()){
                tx.get().rollback();
            }
            return null;
        } finally {
            if (s.isPresent()){
                s.get().close();
            }
        }
        return t;
    }

    public Set<T> getAll(){
        Optional<Session> s = Optional.empty();
        Optional<Transaction> tx = Optional.empty();

        Set<T> set = new HashSet<>();
        try {

            s = Optional.ofNullable(Database.getSession());
            if (s.isPresent()){
                tx = Optional.ofNullable(s.get().getTransaction());
                if (tx.isPresent()){
                    tx.get().begin();
                    Query q = s.get().createQuery("Select o FROM "+getInnerClass().getSimpleName()+" o");

                    final Iterator<T> it = q.iterate();
                    while(it.hasNext()){
                        T t = it.next();
                        initObject(t);
                        set.add(t);
                    }
                    tx.get().commit();
                }
            }

        } catch (Exception e){
            if (tx.isPresent()){
                tx.get().rollback();
            }
            return null;
        } finally {
            if (s.isPresent()){
                s.get().close();
            }
        }
        return set;

    }
}
