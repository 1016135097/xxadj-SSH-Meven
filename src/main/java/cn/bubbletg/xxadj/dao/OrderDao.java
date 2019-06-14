package cn.bubbletg.xxadj.dao;

import cn.bubbletg.xxadj.entity.Order;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/13 17:47
 * @description：Order 订单表操作接口
 * @modified By：
 * @version: 1.0.0
 */
public interface OrderDao extends BaseDao<Order> {
    /*
     * create by: BubbleTg
     * description: 订单添加接口，由于BaseDao 实现了五大基本操作，
     *              这里不用再次写接口了。
     * create time: 2019/6/13 17:48
     *
     * @Param: order 用户对象
     */
    // public void add(Order order);

}
