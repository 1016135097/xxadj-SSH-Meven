package cn.bubbletg.xxadj.action;

import cn.bubbletg.xxadj.entity.Orders;
import cn.bubbletg.xxadj.service.OrdersService;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/13 15:52
 * @description：使用模型驱动获得数据
 * @modified By ：
 * @version: 1.0.0
 */
@Transactional
public class OrdersAction extends ActionSupport implements ModelDriven<Orders> {

    /**
     * create by: BubbleTg
     * description: 通过数据模型驱动来获得表单提交的数据，封装到Order 对象里面，
     * create time: 2019/6/13 19:31
     */
    private Orders orders = new Orders();

    @Override
    public Orders getModel() {
        return orders;
    }

    /**
     * create by: BubbleTg
     * description: 属性注入  OrderService 对象，
     * 通过OrderService对象进行具体操作
     * create time: 2019/6/13 19:33
     */
    private OrdersService ordersService;

    public void setOrdersService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }


    /**
     * create by: BubbleTg
     * description: 分页查询
     * create time: 2019/6/16 15:26
     */

    public void pagingQuery() throws IOException {

    }


    /**
     * create by: BubbleTg
     * description: 订单表添加操作，即下单操作
     * create time: 2019/6/13 16:44
     */

    public void add() throws Exception {
        //显示日志信    @Action
        //    public息
        Logger.getLogger(OrdersAction.class).info("--订单操作--------add()方法执行----数据为：");
        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        //像数据库里面插入数据
        ordersService.add(orders);
        HashMap<String, Object> hashMapOrders = new HashMap<>();
        hashMapOrders.put("add_data", true);
        //转换为json
        String json = JSON.toJSONString(hashMapOrders);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(json);

    }

    /**
     * create by: BubbleTg
     * description: 更新订单表
     * create time: 2019/6/14 15:20
     */
    public void update() {
        //显示日志信息
        Logger.getLogger(OrdersAction.class).info("--订单操作--------update()方法执行----");
        //更新
        ordersService.update(orders);
    }


    /**
     * create by: BubbleTg
     * description: 删除，删除不用的订单
     * create time: 2019/6/14 15:21
     */
    public void delete() throws IOException {
        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        //显示日志信息
        Logger.getLogger(OrdersAction.class).info("--订单操作--------delete()方法执行----");
        //删除
        ordersService.delete(orders);
        HashMap<String, Object> hashMapOrders = new HashMap<>();
        hashMapOrders.put("delete_data", true);
        //转换为json
        String json = JSON.toJSONString(hashMapOrders);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(json);

    }

    /**
     * create by: BubbleTg
     * description: 订单查询操作，首页展示
     * create time: 2019/6/14 15:13
     */
    public void findAll() {
        //显示日志信息
        Logger.getLogger(OrdersAction.class).info("--订单操作--------findAll()方法执行----");
        //订单查询操作，查询全部
        ordersService.findAll();
    }

    /**
     * create by: BubbleTg
     * description: 根据ID查询单条记录
     * create time: 2019/6/14 15:29
     */
    public void findOne() throws IOException {
        //显示日志信息
        Logger.getLogger(OrdersAction.class).info("--订单操作--------findOne()方法执行----");

        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        /*
         通过 HashMap 保存每一次获得的长度,数据，LoadUp返回给前端
         */
        HashMap<String, Object> hashMapOrders = new HashMap<>();
        //根据ID查询单条记录
        Orders ordersOne = ordersService.findOne(orders.getId());

        //是否查询成功判断
        if (ordersOne != null) {
            hashMapOrders.put("OrdersData", ordersOne);
            hashMapOrders.put("success", true);
        } else {
            hashMapOrders.put("success", false);
        }
        //转换为json
        String jsonOrder = JSON.toJSONString(hashMapOrders);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(jsonOrder);

    }

    /**
     * create by: BubbleTg
     * description: 条件查询
     * create time: 2019/6/20 13:31
     */
    public void conditionQuery() throws IOException {
        //显示日志信息
        Logger.getLogger(OrdersAction.class).info("--订单操作--------conditionQuery()方法执行----");

        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        //条件查询
        List<Orders> list = ordersService.conditionQuery(orders);
        HashMap<String, Object> hashMapOrders = new HashMap<>();
        hashMapOrders.put("data", list);
        hashMapOrders.put("dataLength", list.size());
        //转换为json
        String json = JSON.toJSONString(hashMapOrders);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(json);
    }
}