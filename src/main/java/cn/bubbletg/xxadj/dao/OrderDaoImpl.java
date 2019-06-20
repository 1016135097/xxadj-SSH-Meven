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

        //查询
        List<Order> orders = null;
        //判断是全局查找还是附近查找
        if (initialPositionLatitudeMin != initialPositionLatitudeMax) {
            Logger.getLogger(OrderDaoImpl.class).info("-------findPage()方法执行----附近查找");
            //附近查找
            orders = this.getSessionFactory().getCurrentSession()
                    .createQuery("from Order where ifAccept=? and ifFinish =? and initialPositionLatitude > ?" +
                            " and initialPositionLatitude < ? and initialPositionLongitude > ? " +
                            "and initialPositionLongitude < ? and receivedBy = ?")
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
            Logger.getLogger(OrderDaoImpl.class).info("-------findPage()方法执行----全局查找");
            //全局
            orders = this.getSessionFactory().getCurrentSession()
                    .createQuery("from Order where ifAccept=? and ifFinish =? and receivedBy = ?")
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
                .createQuery("from Order where ifAccept=? and ifFinish =? and initialPositionLatitude <= ?" +
                        " and initialPositionLatitude >= ? and initialPositionLongitude <= ? " +
                        "and initialPositionLongitude >= ? and receivedBy = ?")
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

    /**
     * create by: BubbleTg
     * description: 终点位置模糊查询
     * create time: 2019/6/19 14:38
     *
     * @param ifAccept        表示是否被接单
     * @param ifFinish        表示是否完成
     * @param receivedBy      表示被指定的接单人
     * @param initialPosition 表示终点位置模糊查询输入的值
     * @return: List<Order> 返回的订单集合
     */
    @Override
    public List<Order> fuzzyQueryInitialPosition(String receivedBy, boolean ifAccept, boolean ifFinish, String initialPosition) {
        Logger.getLogger(OrderDaoImpl.class).info("-------fuzzyQueryInitialPosition()方法执行----initialPosition = " + initialPosition);
        List<Order> orders = this.getSessionFactory().getCurrentSession()
                .createQuery("from Order where initialPosition like ? and ifAccept=? and ifFinish =? and receivedBy = ? ")
                //设置问号参数
                .setParameter(0, "%" + initialPosition + "%")
                .setParameter(1, ifAccept)
                .setParameter(2, ifFinish)
                .setParameter(3, receivedBy)

                .list();
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
    @Override
    public List<Order> fuzzyQueryFinalPosition(String receivedBy, boolean ifAccept, boolean ifFinish, String finalPosition) {
        Logger.getLogger(OrderDaoImpl.class).info("-------fuzzyQueryFinalPosition()方法执行----");
        //查询，hibernate 的HQL查询
        List<Order> orders = this.getSessionFactory().getCurrentSession()
                .createQuery("from Order where finalPosition like ?  and ifAccept=? and ifFinish =? and receivedBy = ?")
                //设置问号参数
                .setParameter(0, "%" + finalPosition + "%")
                .setParameter(1, ifAccept)
                .setParameter(2, ifFinish)
                .setParameter(3, receivedBy)
                .list();
        return orders;
    }

    /**
     * create by: BubbleTg
     * description: 条件查询
     * create time: 2019/6/20 13:45
     *
     * @param order 条件查询，模型驱动获得数据封装在Order对象里面
     * @return Order对象集合
     */
    @Override
    public List<Order> conditionQuery(Order order) {

        List<Order> orders = this.getSessionFactory().getCurrentSession()
                .createQuery("from Order where  ifFinish =? and openid = ?")
                //设置问号参数
                .setParameter(0, order.isIfFinish())
                .setParameter(1, order.getOpenid())
                .list();
        return orders;
    }


}
