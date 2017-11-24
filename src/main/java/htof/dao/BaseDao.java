package htof.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

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
    PageList<T> selectPageList(T obj, PageBounds pg);
    public int insertInBatch(List<T> obj);//批量插入
}
