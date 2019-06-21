package cn.bubbletg.xxadj.action;

import cn.bubbletg.xxadj.entity.Message;
import cn.bubbletg.xxadj.service.MessageService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.transaction.annotation.Transactional;


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
}