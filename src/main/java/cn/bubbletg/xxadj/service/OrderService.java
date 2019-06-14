package cn.bubbletg.xxadj.service;

import cn.bubbletg.xxadj.dao.OrderDao;
import cn.bubbletg.xxadj.entity.Order;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/13 18:28
 * @description：
 * @modified By：
 * @version: 1.0.0
 */
@Transactional
public class OrderService {
    /**
     * OrderDao 对象，提供spring配置文件注入
     */
    private OrderDao userDao;
    public void setOrderDao(OrderDao userDao) {
        this.userDao = userDao;
    }

    /**
     * create by: BubbleTg
     * description: 订单表添加操作，即下单操作
     * create time: 2019/6/13 19:53
     */
    public void add(Order user) {
        Logger.getLogger(OrderService.class).info("--订单操作-----add()方法执行----");
        //插入数据
        userDao.add(user);
    }

    /**
     * create by: BubbleTg
     * description: 更新订单表
     * create time: 2019/6/14 15:20
     */
    public void update(Order user) {
        Logger.getLogger(OrderService.class).info("--订单操作-----update()方法执行----");
        //更新
        userDao.update(user);

    }

    /**
     * create by: BubbleTg
     * description: 删除，删除不用的订单
     * create time: 2019/6/14 15:21
     */
    public void delete(Order user) {
        Logger.getLogger(OrderService.class).info("--订单操作-----delete()方法执行----");
        //删除
        userDao.delete(user);
    }

    /**
     * create by: BubbleTg
     * description: 首页加载全部查询，查询全部
     * create time: 2019/6/14 15:16
     */
    public void findAll() {
        Logger.getLogger(OrderService.class).info("--订单操作-----findAll()方法执行----");
        //查询所有数据
        userDao.findAll();
    }


    /**
     * create by: BubbleTg
     * description: 根据ID查询单条记录
     * create time: 2019/6/14 15:29
     */
    public Order findOne(int id) {
        Logger.getLogger(OrderService.class).info("--订单操作-----findOne()方法执行----");
        //查询所有数据
        return userDao.findOne(id);
    }
}
