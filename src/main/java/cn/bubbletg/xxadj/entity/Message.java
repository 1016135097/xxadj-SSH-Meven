package cn.bubbletg.xxadj.entity;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/13 15:52
 * @description：信息实体类，用户之间信息通知。
 * @modified By ：
 * @version: 1.0.0
 */
public class Message {
    /**
     * id 信息的 id
     */
    private int id;
    /**
     * creationUserId 创建信息用户的ID
     */
    private int creationUserId;

    /**
     *  embracerUserId 信息接收用户ID
     */
    private int embracerUserId;
    /**
     * creationUserPortrait 头像，创建信息用户头像地址
     */
    private String creationUserPortrait;

    /**
     * messageContent  信息内容
     */
    private String messageContent;
    /**
     *  orderId 关联的订单ID
     */
    private int orderId;
    /**
     *  ordersId 关联的接单ID
     */
    private int ordersId;
    /**
     * 表示是信息是否被查看
     */
    private boolean visit;

    /**
     * 创建时间,信息的创建时间
     */
    private String creationTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreationUserId() {
        return creationUserId;
    }

    public void setCreationUserId(int creationUserId) {
        this.creationUserId = creationUserId;
    }

    public int getEmbracerUserId() {
        return embracerUserId;
    }

    public void setEmbracerUserId(int embracerUserId) {
        this.embracerUserId = embracerUserId;
    }

    public String getCreationUserPortrait() {
        return creationUserPortrait;
    }

    public void setCreationUserPortrait(String creationUserPortrait) {
        this.creationUserPortrait = creationUserPortrait;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public boolean isVisit() {
        return visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", creationUserId=" + creationUserId +
                ", embracerUserId=" + embracerUserId +
                ", creationUserPortrait='" + creationUserPortrait + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", orderId=" + orderId +
                ", ordersId=" + ordersId +
                ", visit=" + visit +
                ", creationTime='" + creationTime + '\'' +
                '}';
    }
}
