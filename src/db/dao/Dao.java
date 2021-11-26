package db.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    void save(T t, String sql);
    Optional<T> get(long id);
    List<T> getAll();
    void update(T t);
    void delete(T t);
}
