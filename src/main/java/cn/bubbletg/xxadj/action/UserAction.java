package cn.bubbletg.xxadj.action;

import cn.bubbletg.xxadj.entity.User;
import cn.bubbletg.xxadj.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/14 16:41
 * @description：
 * @modified By：
 * @version: 1.0.0
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

    /**
     * create by: BubbleTg
     * description: 属性注入  userService 对象，
     *              userService
     * create time: 2019/6/14 16:33
     */
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * create by: BubbleTg
     * description: 使用模型驱动获得表单提交的用户信息
     * create time: 2019/6/14 16:43
     */
    private User user = new User();
    @Override
    public User getModel() {
        return user;
    }

    /**
     * create by: BubbleTg
     * description: 用户表添加操作，即注册操作
     * create time: 2019/6/13 16:44
     *
     */
    public void add() throws Exception {
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--------add()方法执行----数据为：");
        System.out.println(user);
        //像数据库里面插入数据
        userService.add(user);
    }

    /**
     * create by: BubbleTg
     * description: 更新用户表，即修改用户信息
     * create time: 2019/6/14 15:20
     */
    public void update(){
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--------update()方法执行----");
        //更新
        userService.update(user);
    }


    /**
     * create by: BubbleTg
     * description: 删除，删除不用的用户，即销毁账号
     * create time: 2019/6/14 15:21
     */
    public void delete(){
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--------delete()方法执行----");
        //删除
        userService.delete(user);
    }

    /**
     * create by: BubbleTg
     * description: 用户查询操作，查询全部用户
     * create time: 2019/6/14 15:13
     */
    public void findAll(){
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("----------findAll()方法执行----");
        //查询全部用户
        userService.findAll();
    }

    /**
     * create by: BubbleTg
     * description: 根据ID查询单条记录
     * create time: 2019/6/14 15:29
     */
    public void findOne(){
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--------findOne()方法执行----");
        //根据ID查询单条记录
        userService.findOne(user.getId());
    }
}
