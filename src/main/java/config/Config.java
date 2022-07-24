package config;

import java.util.List;

public interface Config<T> {
    List<T> list();

    T get(String id);

    void delete(String id);

    void add(T t);
}
