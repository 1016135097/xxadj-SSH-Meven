package cn.bubbletg.xxadj.dao;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/13 18:01
 * @description：BaseDao 接口实现
 * @modified By：
 * @version: 1.0.0
 */
@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    private Class<T> clazzType;

    // 构造方法
    public BaseDaoImpl() {
        //1.得到当前运行类的Class
        Class c = this.getClass();
        //2.得到运行类的父类的参数化类型
        Type type = c.getGenericSuperclass();
        //转换成子接口ParameterizedType
        ParameterizedType ptype = (ParameterizedType) type;
        //3.得到实际类型参数
        Type[] types = ptype.getActualTypeArguments();
        /*
         * 把Type变成class
         * Class clazzParameter =  (Class) types[0];
         * this.clazzType = clazzParameter;
         */
        this.clazzType = (Class<T>) types[0];

    }

    /**
     * create by: BubbleTg
     * description: 添加方法，把数据添加到数据库
     * create time: 2019/6/13 17:48
     *
     * @return id 用户表  ID，唯一
     * @Param: t 操作的对象
     */
    @Override
    public int add(T t) {
        Logger.getLogger(BaseDaoImpl.class).info("--------add()方法执行----");
        //向数据库添加（插入）数据
        int id = (int) this.getHibernateTemplate().save(t);
        return id;
    }

    /**
     * create by: BubbleTg
     * description: 更新方法，更新到数据库
     * create time: 2019/6/13 17:48
     *
     * @Param: t 操作的对象
     */
    @Override
    public void update(T t) {
        Logger.getLogger(BaseDaoImpl.class).info("--------update()方法执行----");
        //更新
        this.getHibernateTemplate().update(t);
    }

    /**
     * create by: BubbleTg
     * description: 删除方法，把数据从数据库删除
     * create time: 2019/6/13 17:48
     *
     * @Param: t 操作的对象
     */
    @Override
    public void delete(T t) {
        Logger.getLogger(BaseDaoImpl.class).info("--------delete()方法执行----");
        this.getHibernateTemplate().delete(t);
    }

    /**
     * create by: BubbleTg
     * description: 根据ID查询方法
     * create time: 2019/6/13 17:48
     *
     * @Param: id 更加ID来进行查询
     * @return: 返回T 对象，封装查询到的数据
     */
    @Override
    public T findOne(int id) {
        Logger.getLogger(BaseDaoImpl.class).info("-------findOne()方法执行----");
        return this.getHibernateTemplate().get(clazzType, id);
    }

    /**
     * create by: BubbleTg
     * description: 查询全部，返回多条记录
     * create time: 2019/6/13 17:48
     *
     * @Param: T 操作的对象
     * @return: 返回T 对象集合，封装查询到的数据
     */
    @Override
    public List<T> findAll() {
        Logger.getLogger(BaseDaoImpl.class).info("-------findAll()方法执行----");
        //使用getSimpleName() 得到类的名称
        return (List<T>) this.getHibernateTemplate().
                find("from " + clazzType.getSimpleName());
    }
}
