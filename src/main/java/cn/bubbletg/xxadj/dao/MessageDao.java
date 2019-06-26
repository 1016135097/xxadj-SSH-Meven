package cn.bubbletg.xxadj.dao;

import cn.bubbletg.xxadj.entity.Message;

import java.util.List;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/21 17:46
 * @description：
 * @modified By：信息管理接口
 * @version:1.0.0
 */
public interface MessageDao extends BaseDao<Message>{

    /**
     * create by: wang
     * description: 查询是否还有没有查看的消息
     * create time: 2019/6/21 20:32
     *
     * @Param: null
     * @return
     */
    Boolean unread(Message message);

    List<Message> findAll(Message message);


}
