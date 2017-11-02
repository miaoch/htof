package htof.dao;

import java.util.List;

/**
 * Created by miaoch on 2017/11/2.
 */
public interface BaseDao<T> {
    public int insert(T obj);
    public int update(T obj);
    public List<T> select(T obj);
    public int delete(T obj);
    public int saveOrUpdate(T obj);
}
