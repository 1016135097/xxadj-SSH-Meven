package cn.bubbletg.xxadj.dao;

import cn.bubbletg.xxadj.entity.Order;
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
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

    /**
     * create by: BubbleTg
     * description: 获得总记录数据
     * create time: 2019/6/16 15:33
     *
     * @return 总记录数据
     */
    @Override
    public int findCount() {
        Logger.getLogger(OrderDaoImpl.class).info("-------findCount()方法执行----");
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
     * @param begin                       开始查询位置
     * @param pageSize                    每页显示数
     * @param initialPositionLatitudeMin  起始位置纬度附近最小值
     * @param initialPositionLatitudeMax  起始位置纬度附近最大值
     * @param initialPositionLongitudeMin 起始位置经度附近最小值
     * @param initialPositionLongitudeMax 起始位置经度附近最大值
     * @param ifAccept                    表示是否被接单
     * @param ifFinish                    表示是否完成
     * @param receivedBy                  表示被指定的接单人
     * @return: list 返回查询到的多条记录
     */
    public List<Order> findPage(int begin, int pageSize,
                                Double initialPositionLatitudeMin, Double initialPositionLatitudeMax,
                                Double initialPositionLongitudeMin, Double initialPositionLongitudeMax,
                                boolean ifAccept, boolean ifFinish, String receivedBy) {
        Logger.getLogger(OrderDaoImpl.class).info("-------findPage()方法执行----");
        /**
         * 分页查询，也是多条件查询，查询条件包括
         * 附近查询：
         *  --附近查询包括附近与不是附近查询：
         *  --判断是否附近查询：经纬度最大最小之相等为依据
         *      --相等为全局查询，即不是附近查询
         *      --不相等为附近查询
         * 是否完成：
         *  --根据ifFinish值来判断
         * 是否被接单：
         * --根据ifAccept值来判断
         */
        String sql = null;
        //查询
        List<Order> orders = null;
        //判断是全局查找还是附近查找
        if (initialPositionLatitudeMin == initialPositionLatitudeMin) {
            //附近查找
            sql = "from Order where ifAccept=? and ifFinish =? and initialPositionLatitude > ?" +
                    " and initialPositionLatitude < ? and initialPositionLongitude > ? " +
                    "and initialPositionLongitude < ? and receivedBy = ?";
            orders = this.getSessionFactory().getCurrentSession()
                    .createQuery(sql)
                    //设置问号参数
                    .setParameter(0, ifAccept)
                    .setParameter(1, ifFinish)
                    .setParameter(2, initialPositionLatitudeMin)
                    .setParameter(3, initialPositionLatitudeMax)
                    .setParameter(4, initialPositionLongitudeMin)
                    .setParameter(5, initialPositionLongitudeMax)
                    .setParameter(6, receivedBy)
                    //分页查询
                    .setFirstResult(begin)
                    .setMaxResults(pageSize)
                    .list();
        } else {
            //全局
            sql = "from Order where ifAccept=? and ifFinish =? and receivedBy = ?";
            orders = this.getSessionFactory().getCurrentSession()
                    .createQuery(sql)
                    //设置问号参数
                    .setParameter(0, ifAccept)
                    .setParameter(1, ifFinish)
                    .setParameter(2, receivedBy)
                    //分页查询
                    .setFirstResult(begin)
                    .setMaxResults(pageSize)
                    .list();
        }
        return orders;
    }

    /**
     * create by: BubbleTg
     * description: 分页查询操作，附近查询完后查全局
     * create time: 2019/6/16 16:03
     *
     * @param begin                       开始查询位置
     * @param pageSize                    每页显示数
     * @param initialPositionLatitudeMin  起始位置纬度附近最小值
     * @param initialPositionLatitudeMax  起始位置纬度附近最大值
     * @param initialPositionLongitudeMin 起始位置经度附近最小值
     * @param initialPositionLongitudeMax 起始位置经度附近最大值
     * @param ifAccept                    表示是否被接单
     * @param ifFinish                    表示是否完成
     * @param receivedBy                  表示被指定的接单人
     * @return: list 返回查询到的多条记录
     */
    @Override
    public List<Order> findPageNearbyFull(int begin, Integer pageSize,
                                          Double initialPositionLatitudeMin, Double initialPositionLatitudeMax,
                                          Double initialPositionLongitudeMin, Double initialPositionLongitudeMax,
                                          boolean ifAccept, boolean ifFinish, String receivedBy) {
        Logger.getLogger(OrderDaoImpl.class).info("-------findPageNearbyFull()方法执行----");
        List<Order> orders = this.getSessionFactory().getCurrentSession()
                .createQuery("from Order where ifAccept=? and ifFinish =? and initialPositionLatitude < ?" +
                        " and initialPositionLatitude > ? and initialPositionLongitude < ? " +
                        "and initialPositionLongitude > ? and receivedBy = ?")
                //设置问号参数
                .setParameter(0, ifAccept)
                .setParameter(1, ifFinish)
                .setParameter(2, initialPositionLatitudeMin)
                .setParameter(3, initialPositionLatitudeMax)
                .setParameter(4, initialPositionLongitudeMin)
                .setParameter(5, initialPositionLongitudeMax)
                .setParameter(6, receivedBy)
                //分页查询
                .setFirstResult(begin)
                .setMaxResults(pageSize)
                .list();
        return orders;
    }


}
