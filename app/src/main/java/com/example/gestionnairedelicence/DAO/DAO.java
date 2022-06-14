package com.example.gestionnairedelicence.DAO;

import java.util.List;

public abstract class DAO<T> {
    public abstract int create(T unObjet);

    public abstract void update(T unObjet);

    public abstract void delete(T unObjet);

    public abstract List<T> read();
}
