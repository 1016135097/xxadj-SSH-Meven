package cn.bubbletg.xxadj.dao;

import cn.bubbletg.xxadj.entity.Order;
import cn.bubbletg.xxadj.entity.Orders;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/13 17:50
 * @description：订单表操作具体实现
 * @modified By：
 * @version: 1.0.0
 */
@SuppressWarnings("all")
public class OrdersDaoImpl extends BaseDaoImpl<Orders> implements OrdersDao {

    /**
     * create by: BubbleTg
     * description: 获得总记录数据
     * create time: 2019/6/16 15:33
     *
     * @return 总记录数据
     */
    @Override
    public int findCount() {
        Logger.getLogger(OrdersDaoImpl.class).info("-------findCount()方法执行----");
        List<Order> list = (List<Order>) this.getHibernateTemplate().find("select count(*) from Order");
        // 从list中得到
        if (list != null && list.size() != 0) {
            Object obj = list.get(0);
            Long lobj = (Long) obj;
            int count = lobj.intValue();
            return count;
        }
        return 0;
    }



}
