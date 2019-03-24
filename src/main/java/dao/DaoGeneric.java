package dao;

public interface DaoGeneric<T> {

    public void create(T t);

    public void read(T t);

    public void update(T t);

    public void delete(T t);
}
