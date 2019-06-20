package cn.bubbletg.xxadj.service;

import cn.bubbletg.xxadj.dao.OrdersDao;
import cn.bubbletg.xxadj.entity.Order;
import cn.bubbletg.xxadj.entity.Orders;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/13 18:28
 * @description：
 * @modified By：
 * @version: 1.0.0
 */
@Transactional
public class OrdersService {
    /**
     * OrderDao 对象，提供spring配置文件注入
     */
    private OrdersDao ordersDao;

    public void setOrdersDao(OrdersDao ordersDao) {
        this.ordersDao = ordersDao;
    }

    /**
     * create by: BubbleTg
     * description: 订单表添加操作，即下单操作
     * create time: 2019/6/13 19:53
     * @param order
     */
    public void add(Orders order) {
        Logger.getLogger(OrdersService.class).info("--订单操作-----add()方法执行----");
        //插入数据
        ordersDao.add(order);
    }

    /**
     * create by: BubbleTg
     * description: 更新订单表
     * create time: 2019/6/14 15:20
     * @param order
     */
    public void update(Orders order) {
        Logger.getLogger(OrdersService.class).info("--订单操作-----update()方法执行----");
        //更新
        ordersDao.update(order);

    }

    /**
     * create by: BubbleTg
     * description: 删除，删除不用的订单
     * create time: 2019/6/14 15:21
     * @param order
     */
    public void delete(Orders order) {
        Logger.getLogger(OrdersService.class).info("--订单操作-----delete()方法执行----");
        //删除
        ordersDao.delete(order);
    }

    /**
     * create by: BubbleTg
     * description: 首页加载全部查询，查询全部
     * create time: 2019/6/14 15:16
     */
    public void findAll() {
        Logger.getLogger(OrdersService.class).info("--订单操作-----findAll()方法执行----");
        //查询所有数据
        ordersDao.findAll();
    }


    /**
     * create by: BubbleTg
     * description: 根据ID查询单条记录
     * create time: 2019/6/14 15:29
     */
    public Orders findOne(int id) {
        Logger.getLogger(OrdersService.class).info("--订单操作-----findOne()方法执行----");
        //查询所有数据
        return ordersDao.findOne(id);
    }

    /**
     * create by: BubbleTg
     * description: 获得Orders表总记录数
     * create time: 2019/6/16 18:00
     *
     * @return Order表总记录数
     */
    public int findCount() {
        return ordersDao.findCount();
    }

    /**
     * create by: BubbleTg
     * description: 条件查询
     * create time: 2019/6/20 13:42
     *
     * @param order 条件查询，模型驱动获得数据封装在Order对象里面
     * @return Order对象集合
     */
    public List<Orders> conditionQuery(Orders orders) {
        Logger.getLogger(OrderService.class).info("--订单操作-----conditionQuery()方法执行----");
        return ordersDao.conditionQuery(orders);
    }
}



























