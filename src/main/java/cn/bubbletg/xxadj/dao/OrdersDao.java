package cn.bubbletg.xxadj.dao;

import cn.bubbletg.xxadj.entity.Orders;

import java.util.List;

/**
 * @author ：zll
 * @date ：Created in 2019/6/13 17:47
 * @description：Order 订单表操作接口
 * @modified By：
 * @version: 1.0.0
 */
public interface OrdersDao extends BaseDao<Orders> {

    /**
     * create by: zll
     * description: 获得总记录数据
     * create time: 2019/6/16 15:33
     *
     * @return 总记录数据
     */
    int findCount();
    /**
     * create by: zll
     * description: 条件查询
     * create time: 2019/6/20 13:45
     *
     * @param orders 条件查询，模型驱动获得数据封装在Order对象里面
     * @return Order对象集合
     */
    List<Orders> conditionQuery(Orders orders);
}
