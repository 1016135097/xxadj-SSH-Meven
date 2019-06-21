package cn.bubbletg.xxadj.dao;

import cn.bubbletg.xxadj.entity.Message;

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
     * create by: BubbleTg
     * description: 查询是否还有没有查看的消息
     * create time: 2019/6/21 20:30
     *
     * @Param: null
     * @return
     */
    @Override
    public Boolean unread(Message message) {
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
}
