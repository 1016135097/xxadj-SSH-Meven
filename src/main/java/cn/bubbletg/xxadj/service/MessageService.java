package cn.bubbletg.xxadj.service;

import cn.bubbletg.xxadj.dao.MessageDao;
import cn.bubbletg.xxadj.entity.Message;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/21 17:51
 * @description：MessageService 类
 * @modified By：
 * @version: 1.0.0
 */
@Transactional
public class MessageService {
    private MessageDao messageDao;

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }


    /**
     * create by: BubbleTg
     * description: 信息添加
     * create time: 2019/6/21 18:46
     *
     * @param message 数据封装在message
     * @Param: null
     */
    public int add(Message message) {
        return messageDao.add(message);
    }

    /**
     * create by: BubbleTg
     * description: 更新信息
     * create time: 2019/6/21 18:52
     *
     * @Param: null
     */
    public void update(Message message) {

    }

    /**
     * create by: BubbleTg
     * description: 查询一个信息
     * create time: 2019/6/21 18:53
     *
     * @Param: null
     */
    public Message findOne(int id) {
        return messageDao.findOne(id);
    }

    /**
     * create by: BubbleTg
     * description: 删除
     * create time: 2019/6/21 18:59
     *
     * @return
     * @Param: null
     */
    public void delete(Message message) {
        messageDao.delete(message);
    }

    /**
     * create by: BubbleTg
     * description: 查询全部信息
     * create time: 2019/6/21 19:02
     *
     * @return 返回信息集合
     * @Param:
     */
    public List<Message> findAll() {
        return messageDao.findAll();
    }

    /**
     * create by: BubbleTg
     * description: 查询是否还有没有查看的消息
     * create time: 2019/6/21 20:32
     * 
      * @Param: null
     * @return 
     */
    public Boolean unread(Message message) {
        return messageDao.unread(message);
    }
}
