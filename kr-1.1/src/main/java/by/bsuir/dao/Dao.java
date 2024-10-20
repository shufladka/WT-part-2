package by.bsuir.dao;

import java.util.List;

public interface Dao<T> {
    void save(T t);
    List<T> findAll();
    String escapeCyrillicSymbol(String json);
}
