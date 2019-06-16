package cn.bubbletg.xxadj.dao;

import cn.bubbletg.xxadj.entity.Order;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/13 17:50
 * @description：订单表操作具体实现
 * @modified By：
 * @version: 1.0.0
 */
@SuppressWarnings("all")
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao{

    /**
     * create by: BubbleTg
     * description: 获得总记录数据
     * create time: 2019/6/16 15:33
     * @return  总记录数据
     */
    @Override
    public int findCount() {
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

    /**
     * create by: BubbleTg
     * description: 分页查询操作
     * create time: 2019/6/16 16:03
     *
     * @param begin 开始查询位置
     * @param pageSize 每页显示数
     * @return: list 返回查询到的多条记录
     */
    public List<Order> findPage(int begin, int pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Order.class);
        List<Order> list = (List<Order>) this.getHibernateTemplate().
                findByCriteria(detachedCriteria,begin,pageSize);
        return list;
    }
}
