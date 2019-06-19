package cn.bubbletg.xxadj.dao;

import cn.bubbletg.xxadj.entity.Orders;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/13 17:47
 * @description：Order 订单表操作接口
 * @modified By：
 * @version: 1.0.0
 */
public interface OrdersDao extends BaseDao<Orders> {

    /**
     * create by: BubbleTg
     * description: 获得总记录数据
     * create time: 2019/6/16 15:33
     *
     * @return 总记录数据
     */
    int findCount();
}
