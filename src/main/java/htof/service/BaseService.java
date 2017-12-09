package htof.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import htof.dao.BaseDao;

import java.util.List;

/**
 * Created by miaoch on 2017/12/9.
 */
public abstract class BaseService<T> {
    abstract BaseDao<T> getBaseDao();
    public int insert(T obj) {
        return getBaseDao().insert(obj);
    }
    public int update(T obj) {
        return getBaseDao().update(obj);
    }
    public List<T> select(T obj) {
        return getBaseDao().select(obj);
    }
    public List<T> selectAll() {
        return getBaseDao().select(null);
    }
    public int delete(T obj) {
        return getBaseDao().delete(obj);
    }
    public int saveOrUpdate(T obj) {
        return getBaseDao().saveOrUpdate(obj);
    }
    public PageList<T> selectPageList(T obj, PageBounds pg) {
        return getBaseDao().selectPageList(obj, pg);
    }
    public int insertInBatch(List<T> obj) {
        return getBaseDao().insertInBatch(obj);
    }
}
