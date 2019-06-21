package cn.bubbletg.xxadj.action;

import cn.bubbletg.xxadj.entity.Message;
import cn.bubbletg.xxadj.service.MessageService;
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
public class MessageAction extends ActionSupport implements ModelDriven<Message> {

    /**
     * create by: BubbleTg
     * description: 通过数据模型驱动来获得表单提交的数据，封装到Message 对象里面，
     * create time: 2019/6/13 19:31
     */
    private Message message = new Message();

    @Override
    public Message getModel() {
        return message;
    }

    private MessageService messageService;

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void add() throws Exception {
        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        //显示日志信息
        Logger.getLogger(MessageAction.class).info("--------add()方法执行----");
        //id 当插入失败使，返回-1
        int id = -1;
        //像数据库里面插入数据
        id = messageService.add(message);
        //转换为json
        HashMap<String, Integer> data = new HashMap<>();
        data.put("id", id);
        String dataJson = JSON.toJSONString(data);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(dataJson);
    }

    public void update() throws IOException {
        //显示日志信息
        Logger.getLogger(MessageAction.class).info("--------update()方法执行----");
        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        //先查询原来信息
        Message message1 = messageService.findOne(message.getId());
        //需要更新的信息
        message1.setVisit(message.isVisit());

        messageService.update(message1);
        //转换为json
        HashMap<String, Object> data = new HashMap<>();
        data.put("update_date", true);
        String dataJson = JSON.toJSONString(data);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(dataJson);
    }

    /**
     * create by: BubbleTg
     * description: 删除信息
     * create time: 2019/6/21 18:56
     *
     * @return
     * @Param: null
     */
    public void delete() throws IOException {
        //显示日志信息
        Logger.getLogger(MessageAction.class).info("--------delete()方法执行----");
        //删除
        messageService.delete(message);
        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        //转换为json
        HashMap<String, Object> data = new HashMap<>();
        data.put("delete_date", true);
        String dataJson = JSON.toJSONString(data);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(dataJson);

    }

    /**
     * create by: BubbleTg
     * description: 查询全部信息
     * create time: 2019/6/21 19:01
     *
     * @return
     * @Param: null
     */
    public void findAll() throws IOException {
        //显示日志信息
        Logger.getLogger(MessageAction.class).info("--------delete()方法执行----");
        //设置返回类型
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        //设置返回数据编码
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        List<Message> messages = messageService.findAll();
        //转换为json
        HashMap<String, Object> data = new HashMap<>();
        data.put("date", messages);
        String dataJson = JSON.toJSONString(data);
        //传递给前端
        ServletActionContext.getResponse().getWriter().write(dataJson);

    }


}