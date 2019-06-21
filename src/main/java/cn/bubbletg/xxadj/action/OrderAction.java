package cn.bubbletg.xxadj.action;

import cn.bubbletg.xxadj.entity.Order;
import cn.bubbletg.xxadj.service.OrderService;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

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
     * description: 模糊查询
     * create time: 2019/6/19 14:16
     *
     * @Param: null
     */
    public void fuzzyQuery() throws IOException {

        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--订单操作--------fuzzyQuery()方法执行---");
        //获得请求域对象
        HttpServletRequest request = ServletActionContext.getRequest();
        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        /*
         通过 HashMap 保存每一次获得的长度,数据，LoadUp返回给前端
         */
        HashMap<String, Object> hashMapOrders = new HashMap<>();
        //查询并获得数据,执行起始位置模糊查询
        List<Order> orders = orderService.fuzzyQueryInitialPosition(order.getReceivedBy(), order.isIfAccept(),
                order.isIfFinish(), order.getInitialPosition());
        hashMapOrders.put("InitialPositionData", orders);
        hashMapOrders.put("InitialPositionDataLength", orders.size()); //保存附近查询到数据的长度
        //查询并获得数据,执行终点位置模糊查询
        orders = orderService.fuzzyQueryFinalPosition(order.getReceivedBy(), order.isIfAccept(),
                order.isIfFinish(), order.getFinalPosition());
        hashMapOrders.put("FinalPositionData", orders);
        hashMapOrders.put("FinalPositionDataLength", orders.size()); //保存附近查询到数据的长度
        //转换为json
        String jsonOrder = JSON.toJSONString(hashMapOrders);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(jsonOrder);
    }

    /**
     * create by: BubbleTg
     * description: 分页查询
     * create time: 2019/6/16 15:26
     */

    public void pagingQuery() throws IOException {
        System.out.println(order);
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--订单操作--------pagingQuery()方法执行---");
        //获得请求域对象
        HttpServletRequest request = ServletActionContext.getRequest();
        //获得当前页
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        //获得当前页面大小
        int pageSize = Integer.valueOf(request.getParameter("pageSize"));
        //起始位置纬度附近最小值
        String initialPositionLatitudeMin = request.getParameter("initialPositionLatitudeMin");
        //起始位置纬度附近最大值
        String initialPositionLatitudeMax = request.getParameter("initialPositionLatitudeMax");
        //起始位置经度附近最小值
        String initialPositionLongitudeMin = request.getParameter("initialPositionLongitudeMin");
        //起始位置经度附近最大值
        String initialPositionLongitudeMax = request.getParameter("initialPositionLongitudeMax");
        //查询并获得数据
        List<Order> orders = orderService.pagingQuery(currentPage, pageSize,
                initialPositionLatitudeMin, initialPositionLatitudeMax,
                initialPositionLongitudeMin, initialPositionLongitudeMax,
                order.isIfAccept(), order.isIfFinish(), order.getReceivedBy());

        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        /*
         通过 HashMap 保存每一次获得的长度,数据，LoadUp返回给前端
         */
        HashMap<String, Object> hashMapOrders = new HashMap<>();
        hashMapOrders.put("data", orders);
        //保存附近查询到数据的长度
        hashMapOrders.put("dataLength", orders.size());
        //判断经纬度最大最小值是否相等，相等表示全局查找，直接返回全局查找数据
        if (!initialPositionLatitudeMin.equals(initialPositionLatitudeMax)) {
            //获得附近之外的全部数据
            orders = orderService.pagingQueryNearbyFull(currentPage, pageSize,
                    initialPositionLatitudeMin, initialPositionLatitudeMax,
                    initialPositionLongitudeMin, initialPositionLongitudeMax,
                    order.isIfAccept(), order.isIfFinish(), order.getReceivedBy());
            //不相等，表示附近查找
            hashMapOrders.put("dataQuan", orders);
            hashMapOrders.put("dataQuanlength", orders.size());
        }

        //获得Order表总记录数
        int totalCount = orderService.findCount();
        //开始的位置   当前页减一乘每页记录数
        int begin = (currentPage - 1) * pageSize;
        /*
        是否加载完毕，用开始位置+查询到的数据长度：比如第一页查询位子是0，
        记录长度2，总长度2，表示查询全部完
         */
        if (begin + orders.size() >= totalCount) {
            //LoadUp 表示加载完，即数据查询完毕
            hashMapOrders.put("LoadUp", true);
        } else {
            hashMapOrders.put("LoadUp", false);
        }

        //转换为json
        String jsonOrder = JSON.toJSONString(hashMapOrders);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(jsonOrder);
    }


    /**
     * create by: BubbleTg
     * description: 订单表添加操作，即下单操作
     * create time: 2019/6/13 16:44
     */

    public void add() throws Exception {
        //显示日志信    @Action
        //    public息
        Logger.getLogger(OrderAction.class).info("--订单操作--------add()方法执行----数据为：");
        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        //像数据库里面插入数据
        orderService.add(order);
        HashMap<String, Object> hashMapOrders = new HashMap<>();
        hashMapOrders.put("add_data", true);
        //转换为json
        String json = JSON.toJSONString(hashMapOrders);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(json);

    }

    /**
     * create by: BubbleTg
     * description: 更新订单表，接单更新
     * create time: 2019/6/14 15:20
     */
    public void update() throws IOException {

        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--订单操作--------update()方法执行----");

        //先查询要更新的订单表
        Order orders = orderService.findOne(order.getId());
        //获得请求域对象
        HttpServletRequest request = ServletActionContext.getRequest();
        //获得跟下标签，用来判断更新的那个字段
        String what = request.getParameter("what");
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        //像数据库里面插入数据
        HashMap<String, Object> hashMapOrders = new HashMap<>();
        //判断更新那个字段
        if (what.equals("ifFinish")) {
            //更新ifFinish
            orders.setIfFinish(order.isIfFinish());
        } else if (what.equals("ifAccept")) {
            orders.setDaijiajiedan_id(order.getDaijiajiedan_id());
            orders.setIfAccept(order.isIfAccept());
        }
        //更新
        orderService.update(orders);
        hashMapOrders.put("update_data", true);
        //转换为json
        String json = JSON.toJSONString(hashMapOrders);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(json);

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
        Logger.getLogger(OrderAction.class).info("--订单操作--------delete()方法执行----");
        //删除
        orderService.delete(order);
        HashMap<String, Object> hashMapOrders = new HashMap<>();
        hashMapOrders.put("delete_data", true);
        //转换为json
        String json = JSON.toJSONString(hashMapOrders);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(json);

    }

    /**
     * create by: BubbleTg
     * description: 条件查询
     * create time: 2019/6/20 13:31
     */
    public void conditionQuery() throws IOException {
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--订单操作--------conditionQuery()方法执行----");

        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        //条件查询
        List<Order> list = orderService.conditionQuery(order);
        HashMap<String, Object> hashMapOrders = new HashMap<>();
        hashMapOrders.put("data", list);
        hashMapOrders.put("dataLength", list.size());
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
        Logger.getLogger(OrderAction.class).info("--订单操作--------findAll()方法执行----");
        //订单查询操作，查询全部
        orderService.findAll();

    }

    /**
     * create by: BubbleTg
     * description: 根据ID查询单条记录
     * create time: 2019/6/14 15:29
     */
    public void findOne() throws IOException {
        //显示日志信息
        Logger.getLogger(OrderAction.class).info("--订单操作--------findOne()方法执行----");

        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        /*
         通过 HashMap 保存每一次获得的长度,数据，LoadUp返回给前端
         */
        HashMap<String, Object> hashMapOrders = new HashMap<>();
        //根据ID查询单条记录
        Order orderOne = orderService.findOne(order.getId());

        //是否查询成功判断
        if (orderOne != null) {
            hashMapOrders.put("OrderData", orderOne);
            hashMapOrders.put("success", true);
        } else {
            hashMapOrders.put("success", false);
        }
        //转换为json
        String jsonOrder = JSON.toJSONString(hashMapOrders);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(jsonOrder);

    }
}