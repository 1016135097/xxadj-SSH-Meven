package cn.bubbletg.xxadj.dao;

import java.util.List;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/13 17:57
 * @description：BaseDao 接口，封装基本操作接口
 * @modified By：
 * @version: 1.0.0
 */
public interface BaseDao<T> {
    /**
     * create by: BubbleTg
     * description: 添加
     * create time: 2019/6/13 17:58
     *
     * @return
     * @Param: null
     */
    int add(T t);

    /**
     * create by: BubbleTg
     * description: 修改
     * create time: 2019/6/13 17:59
     *
     * @Param: null
     */
    void update(T t);

    /**
     * create by: BubbleTg
     * description: 删除
     * create time: 2019/6/13 17:59
     *
     * @Param: null
     */
    void delete(T t);


    /**
     * create by: BubbleTg
     * description: 根据id查询
     * create time: 2019/6/13 17:59
     *
     * @return T  泛型对象
     * @Param: id
     */
    T findOne(int id);


    /**
     * create by: BubbleTg
     * description: 查询所有
     * create time: 2019/6/13 18:00
     *
     * @Param: null
     */
    List<T> findAll();

}
