package cn.bubbletg.xxadj.action;

import cn.bubbletg.xxadj.entity.Order;
import cn.bubbletg.xxadj.service.OrderService;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/13 15:52
 * @description：使用模型驱动获得数据
 * @modified By ：
 * @version: 1.0.0
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

    /**
     * create by: BubbleTg
     * description: 属性注入  OrderService 对象，
     * 通过OrderService对象进行具体操作
     * create time: 2019/6/13 19:33
     */
    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * create by: BubbleTg
     * description: 通过数据模型驱动来获得表单提交的数据，封装到Order 对象里面，
     * create time: 2019/6/13 19:31
     */
    private Order order = new Order();

    @Override
    public Order getModel() {
        return order;
    }


    /**
     * create by: BubbleTg
     * description: 分页查询
     * create time: 2019/6/16 15:26
     */
    public void pagingQuery() throws IOException {
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--订单操作--------pagingQuery()方法执行---");
        //获得请求域对象
        HttpServletRequest request = ServletActionContext.getRequest();
        //获得当前页
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        //获得当前页面大小
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));
        //查询并获得数据
        List<Order> orders = orderService.pagingQuery(currentPage, pageSize);

        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        //转换为json
        String jsonUser = JSON.toJSONString(orders);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(jsonUser);
    }


    /**
     * create by: BubbleTg
     * description: 订单表添加操作，即下单操作
     * create time: 2019/6/13 16:44
     *
     * @Param: null
     */
    public void add() throws Exception {
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--订单操作--------add()方法执行----数据为：");
        System.out.println(order);
        //像数据库里面插入数据
        orderService.add(order);
    }

    /**
     * create by: BubbleTg
     * description: 更新订单表
     * create time: 2019/6/14 15:20
     */
    public void update() {
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--订单操作--------update()方法执行----");
        //更新
        orderService.update(order);
    }


    /**
     * create by: BubbleTg
     * description: 删除，删除不用的订单
     * create time: 2019/6/14 15:21
     */
    public void delete() {
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--订单操作--------delete()方法执行----");
        //删除
        orderService.delete(order);
    }

    /**
     * create by: BubbleTg
     * description: 订单查询操作，首页展示
     * create time: 2019/6/14 15:13
     */
    public void findAll() {
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--订单操作--------findAll()方法执行----");
        //订单查询操作，查询全部
        orderService.findAll();
    }

    /**
     * create by: BubbleTg
     * description: 根据ID查询单条记录
     * create time: 2019/6/14 15:29
     */
    public void findOne() {
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--订单操作--------findOne()方法执行----");
        //根据ID查询单条记录
        orderService.findOne(order.getId());
    }
}