package cn.bubbletg.xxadj.dao;

import cn.bubbletg.xxadj.entity.Orders;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author ：zll
 * @date ：Created in 2019/6/13 17:50
 * @description：订单表操作具体实现
 * @modified By：
 * @version: 1.0.0
 */
@SuppressWarnings("all")
public class OrdersDaoImpl extends BaseDaoImpl<Orders> implements OrdersDao {

    /**
     * create by: zll
     * description: 获得总记录数据
     * create time: 2019/6/16 15:33
     *
     * @return 总记录数据
     */
    @Override
    public int findCount() {
        Logger.getLogger(OrdersDaoImpl.class).info("-------findCount()方法执行----");
        List<Orders> list = (List<Orders>) this.getHibernateTemplate().find("select count(*) from Orders");
        // 从list中得到
        if (list != null && list.size() != 0) {
            Object obj = list.get(0);
            Long lobj = (Long) obj;
            int count = lobj.intValue();
            return count;
        }
        return 0;
    }


    /**
     * create by: zll
     * description: 条件查询
     * create time: 2019/6/20 13:45
     *
     * @param orders 条件查询，模型驱动获得数据封装在Order对象里面
     * @return Orders对象集合
     */
    @Override
    public List<Orders> conditionQuery(Orders orders) {

        List<Orders> orderss = this.getSessionFactory().getCurrentSession()
                .createQuery("from Orders where  ifFinish =? and openid = ?")
                //设置问号参数
                .setParameter(0, orders.isIfFinish())
                .setParameter(1, orders.getOpenid())
                .list();
        return orderss;
    }
}
