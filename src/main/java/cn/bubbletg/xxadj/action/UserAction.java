package cn.bubbletg.xxadj.action;

import cn.bubbletg.xxadj.entity.User;
import cn.bubbletg.xxadj.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;

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
     * userService
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
     */
    public void add() throws Exception {
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--------add()方法执行----数据为：");
        System.out.println(user);
        //id 当插入失败使，返回-1
        int id = -1;
        //插入异常，表示用户重复注册，数据库字段openid设置唯一,所以出错
        try {
            //像数据库里面插入数据
            id = userService.add(user);
        } catch (RuntimeException e) {
            System.out.println("一个账户多次注册！，忽略异常打印，使返回前端数据整洁！");
        } finally {
            ServletActionContext.getResponse().getWriter().write("id=" + id);
            ServletActionContext.getResponse().getWriter().flush();
        }

    }

    /**
     * create by: BubbleTg
     * description: 更新用户表，即修改用户信息
     * create time: 2019/6/14 15:20
     */
    public void update() {


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
    public void delete() {
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
    public void findAll() {
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("----------findAll()方法执行----");
        //查询全部用户
        userService.findAll();
    }

    /**
     * create by: BubbleTg
     * description: 根据ID查询单条记录  TODO
     * create time: 2019/6/14 15:29
     *
     */
    public void findOne() throws IOException {
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--------findOne()方法执行----");
        //根据ID查询单条记录,封装到user对象
        User findUser = userService.findOne(user.getOpenid());


        //获得好的数据，待封装
        ServletActionContext.getResponse().getWriter().write("id="+findUser.getId());


    }

}
