package cn.bubbletg.xxadj.dao;

import cn.bubbletg.xxadj.entity.Message;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/21 17:49
 * @description：信息管理接口实现
 * @modified By：
 * @version: 1.0.0
 */
@SuppressWarnings("all")
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao {

    /**
     * create by: Wang
     * description: 查询是否还有没有查看的消息
     * create time: 2019/6/21 20:30
     *
     * @Param: null
     * @return
     */
    @Override
    public Boolean unread(Message message) {
        Logger.getLogger(MessageDaoImpl.class).info("-------unread()方法执行----");
        Boolean b = false;
       List<Message> message1 =  this.getSessionFactory().getCurrentSession()
                .createQuery("from Message where visit = ? and embracerUserId = ?")
                //设置问号参数
                .setParameter(0, message.isVisit())
               .setParameter(1, message.getEmbracerUserId())
                .list();
       //大于0 表示查询到有没有查看的消息
        if(message1.size()>0){
            b = true;
        }
        return  b;
    }

    /**
     * create by: wang
     * description: 根据当前信息接收用户id查询全部信息
     * create time: 2019/6/21 6:35  19:30
     *
      * @Param: message
     * @return
     */
    public List<Message> findAll(Message message){
        Logger.getLogger(MessageDaoImpl.class).info("-------findAll0a-()方法执行----");
        List<Message> messageList = this.getSessionFactory().getCurrentSession()
                .createQuery("from Message where  embracerUserId =?")
                //设置问号参数
                .setParameter(0, message.getEmbracerUserId())
                .list();
        return messageList;
    }
}
