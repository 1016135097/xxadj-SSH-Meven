package cn.bubbletg.xxadj.action;

import cn.bubbletg.xxadj.entity.User;
import cn.bubbletg.xxadj.service.UserService;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/14 16:41
 * @description：
 * @modified By：
 * @version: 1.0.0
 */
@Transactional
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
        System.out.println(user);
        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
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
        }
        System.out.println("转换为json---------------------------");
        //转换为json
        HashMap<String, Integer> data = new HashMap<>();
        data.put("id", id);
        String dataJson = JSON.toJSONString(data);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(dataJson);


    }

    /**
     * create by: BubbleTg
     * description: 更新用户表，即修改用户信息
     * create time: 2019/6/14 15:20
     */
    public void update() throws IOException {
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--------update()方法执行----");

        //更新
        userService.update(user);
        //更新成功后，重新查询用户数据，并返回数据
        // userService.findOne(user.getOpenid());
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
     * description: 根据ID查询单条记录
     * create time: 2019/6/14 15:29
     */
    public void findOne() throws IOException {
        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--------findOne()方法执行----");
        //根据ID查询单条记录,封装到user对象
        User findUser = userService.findOne(user.getOpenid());
        //转换为json
        String jsonUser = JSON.toJSONString(findUser);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(jsonUser);
    }

}
