package cn.bubbletg.xxadj.service;

import cn.bubbletg.xxadj.dao.UserDao;
import cn.bubbletg.xxadj.entity.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/14 16:44
 * @description：
 * @modified By：
 * @version: 1.0.0
 */
@Transactional
public class UserService {
    /**
     * OrderDao 对象，提供spring配置文件注入
     */
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * create by: zck
     * description: 用户表添加操作，即注册操作
     * create time: 2019/6/13 16:44
     *
     * @return id 用户表  ID，唯一
     * @Param: user 封装的用户数据
     */
    public int add(User user) {
        return userDao.add(user);
    }

    /**
     * create by: BubbleTg
     * description: 更新用户表，即修改用户信息
     * create time: 2019/6/14 15:20
     *
     * @Param: user 封装的用户数据
     */
    public void update(User user) {
        /*
         * 判断是什么更新，实名认证更新，还是驾驶认证更新，或者其他信息更新
         * 通过  realNameAuthentication 判断是否实名认证更新
         * 通过  drivingCertification 判断是否驾驶认证更新
         * 否则就是其他信息更新
         *
         */
        //
        //User u = userDao.findOne(user.getId());
        //更新前先根据用户ID查询
        User u = null;
        if(user.getRealNameAuthentication().equals("已实名认证")){
            u = userDao.findOne(user.getId());
            //说明实名认证更新
            u.setRealNameAuthentication("已实名认证");
            u.setName(user.getName());
            //更新
            userDao.update(u);

        }else if(user.getDrivingCertification().equals("已驾驶认证")){
            u = userDao.findOne(user.getId());
            //驾驶认证更新
            u.setDrivingCertification("已驾驶认证");
            //更新
            userDao.update(u);

        }else{
            //其他信息更新
            //更新
            userDao.update(user);
        }
    }

    /**
     * create by: BubbleTg
     * description: 删除，删除不用的用户，即销毁账号
     * create time: 2019/6/14 15:21
     *
     * @Param: user 封装的用户数据
     */
    public void delete(User user) {
        userDao.delete(user);
    }

    /**
     * create by: BubbleTg
     * description: 用户查询操作，查询全部用户
     * create time: 2019/6/14 15:13
     */
    public void findAll() {
        userDao.findAll();
    }

    /**
     * create by: BubbleTg
     * description: 根据openid查询单条记录
     * create time: 2019/6/14 15:29
     *
     * @return User user 用户对象，查询到数据封装到User 对象
     */
    public User findOne(String openid) {
        return userDao.findOne(openid);
    }//17863275893张成可
}
