package fr.eni.ecole.enchere.dal;

import java.util.List;

public interface Dao<T> {
    List<T> findAll();
    T findById(int id);
    void save(T t);
    void deleteById(int id);
    void update(T t);
}
