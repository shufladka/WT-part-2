package by.bsuir.dao;

import java.util.List;

public interface Dao<T> {
    void save(T t);
    List<T> findAll(Class<T> genericClass);
    String escapeCyrillicSymbol(String json);
}
