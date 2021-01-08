package se.gu.smart.repository;

import java.util.Collection;

public interface Repository<T> {

    void load(Collection<T> collection);

    Collection<T> getAll();

    void clear();
}
