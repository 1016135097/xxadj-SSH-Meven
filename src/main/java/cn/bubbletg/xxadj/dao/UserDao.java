package cn.bubbletg.xxadj.dao;

import cn.bubbletg.xxadj.entity.User;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/14 16:34
 * @description：UserDao 接口
 * @modified By：
 * @version: 1.0.0
 */
public interface UserDao extends BaseDao<User>{
    /**
     * create by: BubbleTg
     * description: 根据id查询
     * create time: 2019/6/13 17:59
     *
     * @Param: id
     * @return  User 对象
     */
    User findOne(String id);

}
