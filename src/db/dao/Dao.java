package db.dao;

import java.util.List;

public interface Dao<T> {
    void fillTable();
    void save(T t);

    T get(int id);

    List<T> getAll();

    T getLast();

    void update(T t);

    void delete(int id);
}
