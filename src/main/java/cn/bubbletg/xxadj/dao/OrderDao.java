package cn.bubbletg.xxadj.dao;

import cn.bubbletg.xxadj.entity.Order;

import java.util.List;

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

    /**
     * create by: BubbleTg
     * description: 获得总记录数据
     * create time: 2019/6/16 15:33
     *
     * @return 总记录数据
     */
    int findCount();

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
    List<Order> findPage(int begin, int pageSize,
                         Double initialPositionLatitudeMin, Double initialPositionLatitudeMax,
                         Double initialPositionLongitudeMin, Double initialPositionLongitudeMax,
                         boolean ifAccept, boolean ifFinish, String receivedBy);

}
