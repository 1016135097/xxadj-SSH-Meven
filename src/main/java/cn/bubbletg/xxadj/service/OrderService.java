package cn.bubbletg.xxadj.service;

import cn.bubbletg.xxadj.dao.OrderDao;
import cn.bubbletg.xxadj.entity.Order;
import cn.bubbletg.xxadj.entity.Page;
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
public class OrderService {
    /**
     * OrderDao 对象，提供spring配置文件注入
     */
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * create by: Wang
     * description: 订单表添加操作，即下单操作
     * create time: 2019/6/13 19:53
     */
    public void add(Order order) {
        Logger.getLogger(OrderService.class).info("--订单操作-----add()方法执行----");
        //插入数据
        orderDao.add(order);
    }

    /**
     * create by: zll
     * description: 更新订单表
     * create time: 2019/6/14 15:20
     */
    public void update(Order order) {
        Logger.getLogger(OrderService.class).info("--订单操作-----update()方法执行----");
        //更新
        orderDao.update(order);

    }

    /**
     * create by: zll
     * description: 删除，删除不用的订单
     * create time: 2019/6/14 15:21
     */
    public void delete(Order order) {
        Logger.getLogger(OrderService.class).info("--订单操作-----delete()方法执行----");
        //删除
        orderDao.delete(order);
    }

    /**
     * create by: BubbleTg
     * description: 首页加载全部查询，查询全部
     * create time: 2019/6/14 15:16
     */
    public void findAll() {
        Logger.getLogger(OrderService.class).info("--订单操作-----findAll()方法执行----");
        //查询所有数据
        orderDao.findAll();
    }


    /**
     * create by: BubbleTg
     * description: 根据ID查询单条记录
     * create time: 2019/6/14 15:29
     */
    public Order findOne(int id) {
        Logger.getLogger(OrderService.class).info("--订单操作-----findOne()方法执行----");
        //查询所有数据
        return orderDao.findOne(id);
    }

    /**
     * create by: BubbleTg
     * description: 分页查询
     * create time: 2019/6/16 15:27
     *
     * @param currentPage                 当前页
     * @param pageSize                    每页多少条记录
     * @param initialPositionLatitudeMin  起始位置纬度附近最小值
     * @param initialPositionLatitudeMax  起始位置纬度附近最大值
     * @param initialPositionLongitudeMin 起始位置经度附近最小值
     * @param initialPositionLongitudeMax 起始位置经度附近最大值
     * @param ifAccept                    表示是否被接单
     * @param ifFinish                    表示是否完成
     * @param receivedBy                  表示被指定的接单人
     */
    public List<Order> pagingQuery(Integer currentPage, Integer pageSize,
                                   String initialPositionLatitudeMin, String initialPositionLatitudeMax,
                                   String initialPositionLongitudeMin, String initialPositionLongitudeMax,
                                   boolean ifAccept, boolean ifFinish, String receivedBy) {
        Logger.getLogger(OrderService.class).info("--订单操作-----pagingQuery()方法执行----");
        //创建页面对象
        Page<Order> orderPage = new Page<>();
        //设置当前页面
        orderPage.setCurrentPage(currentPage);
        //获得总记录数
        int totalCount = orderDao.findCount();
        //设置总记录数
        orderPage.setTotalCount(totalCount);
        /*
         * 总页数
         * 总记录数除以每页显示记录数
         */
        int totalPage = 0;
        //整除
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }
        //设置总页数
        orderPage.setTotalPage(totalPage);
        //开始的位置   当前页减一乘每页记录数
        int begin = (currentPage - 1) * pageSize;
        //每页记录的Order的list集合
        List<Order> orders = orderDao.findPage(begin, pageSize,
                initialPositionLatitudeMin, initialPositionLatitudeMax,
                initialPositionLongitudeMin, initialPositionLongitudeMax,
                ifAccept, ifFinish, receivedBy);
        //返回
        return orders;
    }

    /**
     * create by: BubbleTg
     * description: 分页查询
     * create time: 2019/6/16 15:27
     *
     * @param currentPage                 当前页
     * @param pageSize                    每页多少条记录
     * @param initialPositionLatitudeMin  起始位置纬度附近最小值
     * @param initialPositionLatitudeMax  起始位置纬度附近最大值
     * @param initialPositionLongitudeMin 起始位置经度附近最小值
     * @param initialPositionLongitudeMax 起始位置经度附近最大值
     * @param ifAccept                    表示是否被接单
     * @param ifFinish                    表示是否完成
     * @param receivedBy                  表示被指定的接单人
     */
    public List<Order> pagingQueryNearbyFull(Integer currentPage, Integer pageSize,
                                             String initialPositionLatitudeMin, String initialPositionLatitudeMax,
                                             String initialPositionLongitudeMin, String initialPositionLongitudeMax,
                                             boolean ifAccept, boolean ifFinish, String receivedBy) {
        Logger.getLogger(OrderService.class).info("--订单操作-----pagingQueryNearbyFull()方法执行----");
        //创建页面对象
        Page<Order> orderPage = new Page<>();
        //设置当前页面
        orderPage.setCurrentPage(currentPage);
        //获得总记录数
        int totalCount = orderDao.findCount();
        //设置总记录数
        orderPage.setTotalCount(totalCount);
        //开始的位置   当前页减一乘每页记录数
        int begin = (currentPage - 1) * pageSize;
        Logger.getLogger(OrderService.class).info("--订单操作-----pagingQueryNearbyFull()方法执行----begin="
                + begin + "===pageSize" + pageSize);
        //每页记录的Order的list集合
        List<Order> orders = orderDao.findPageNearbyFull(begin, pageSize,
                initialPositionLatitudeMin, initialPositionLatitudeMax,
                initialPositionLongitudeMin, initialPositionLongitudeMax,
                ifAccept, ifFinish, receivedBy);
        //返回
        return orders;
    }

    /**
     * create by: BubbleTg
     * description: 获得Order表总记录数
     * create time: 2019/6/16 18:00
     *
     * @return Order表总记录数
     */
    public int findCount() {
        return orderDao.findCount();
    }

    /**
     * create by: BubbleTg
     * description: 起始位置模糊查询
     * create time: 2019/6/19 14:38
     *
     * @param ifAccept        表示是否被接单
     * @param ifFinish        表示是否完成
     * @param receivedBy      表示被指定的接单人
     * @param initialPosition 表示起始位置模糊查询输入的值
     * @return: List<Order> 返回的订单集合
     */
    public List<Order> fuzzyQueryInitialPosition(String receivedBy, boolean ifAccept, boolean ifFinish, String initialPosition) {
        Logger.getLogger(OrderService.class).info("--订单操作-----fuzzyQueryInitialPosition()方法执行----");
        List<Order> orders = orderDao.fuzzyQueryInitialPosition(receivedBy, ifAccept, ifFinish, initialPosition);
        return orders;
    }

    /**
     * create by: BubbleTg
     * description: 终点位置模糊查询
     * create time: 2019/6/19 14:38
     *
     * @param ifAccept      表示是否被接单
     * @param ifFinish      表示是否完成
     * @param receivedBy    表示被指定的接单人
     * @param finalPosition 表示终点位置模糊查询输入的值
     * @return: List<Order> 返回的订单集合
     */
    public List<Order> fuzzyQueryFinalPosition(String receivedBy, boolean ifAccept, boolean ifFinish, String finalPosition) {
        Logger.getLogger(OrderService.class).info("--订单操作-----fuzzyQueryFinalPosition()方法执行----");
        return orderDao.fuzzyQueryFinalPosition(receivedBy, ifAccept, ifFinish, finalPosition);
    }

    /**
     * create by: BubbleTg
     * description: 条件查询
     * create time: 2019/6/20 13:42
     *
     * @param order 条件查询，模型驱动获得数据封装在Order对象里面
     * @return Order对象集合
     */
    public List<Order> conditionQuery(Order order) {
        Logger.getLogger(OrderService.class).info("--订单操作-----conditionQuery()方法执行----");
        return orderDao.conditionQuery(order);
    }
}



























