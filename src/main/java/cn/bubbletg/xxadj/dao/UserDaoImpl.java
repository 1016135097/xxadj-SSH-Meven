package cn.bubbletg.xxadj.dao;

import cn.bubbletg.xxadj.entity.User;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/14 16:36
 * @description：UserDao接口的实现
 * @modified By：
 * @version: 1.0.0
 */
@SuppressWarnings("all")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

    /**
     * create by: BubbleTg
     * description: 根据openid查询，但是openid是String类型
     * create time: 2019/6/15 15:50
     *
     * @Param: openid,微信用户标识符
     * @return  User 封装查询到的数据
     */
    @Override
    public User findOne(String openid) {
        Logger.getLogger(UserDaoImpl.class).info("-------findOne()方法执行----");
        //查询
         List<User>  users= this.getSessionFactory().getCurrentSession()
                 .createQuery("from User where openid=?")
                 .setParameter(0,openid)
                 .list();
         return users.get(0);

    }
}
