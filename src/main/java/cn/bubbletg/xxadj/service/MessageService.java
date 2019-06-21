package cn.bubbletg.xxadj.service;

import cn.bubbletg.xxadj.dao.MessageDao;
import org.springframework.transaction.annotation.Transactional;

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


}
