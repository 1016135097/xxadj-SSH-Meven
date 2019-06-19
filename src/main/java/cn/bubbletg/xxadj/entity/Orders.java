package cn.bubbletg.xxadj.entity;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/13 15:52
 * @description：订单实体类，用户发布代驾产生的订单实体类。
 * @modified By ：
 * @version: 1.0.0
 */
public class Orders {
    /**
     * id 接单的 id，当前id
     */
    private int id;
    /**
     * orderId 订单的 id，
     */
    private int orderId;
    /**
     * 头像，下单着的头像地址
     */
    private String orderPortrait;
    /**
     * 用户名,保存下单着的用户名
     */
    private String orderUsername;
    /**
     * 起始位置,订单信息的起始位置
     */
    private String orderInitialPosition;
    /**
     * 终点位置,订单信息的终点位置
     */
    private String orderFinalPosition;
    /**
     * 联系方式,订单信息的联系方式
     */
    private String orderPhone;
    /**
     * 预约时间,订单信息的预约时间
     */
    private String orderTime;
    /**
     * 添加代驾,订单信息的添加代驾
     */
    private String addGenerationOfDriving;
    /**
     * 包车服务,订单信息的包车服务
     */
    private String charterCarService;
    /**
     * 包时代驾,订单信息的包时代驾
     */
    private String packageTimeDriving;
    /**
     * 起始位置纬度,订单信息的起始位置纬度
     */
    private Double initialPositionLatitude;
    /**
     * 起始位置经度,订单信息的起始位置经度
     */
    private Double initialPositionLongitude;
    /**
     * 终点位置纬度,订单信息的终点位置纬度
     */
    private Double finalPositionLatitude;
    /**
     * 终点位置经度,订单信息的终点位置经度
     */
    private Double finalPositionLongitude;
    /**
     * //表示是否完成
     */
    private boolean ifFinish;
    /**
     * 表示是否被接单
     */
    private boolean ifAccept;
    /**
     * 创建时间,订单信息的创建时间
     */
    private String orderCreationTime;
    /**
     * 接单的创建时间,订单信息的创建时间
     */
    private String creationTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderPortrait() {
        return orderPortrait;
    }

    public void setOrderPortrait(String orderPortrait) {
        this.orderPortrait = orderPortrait;
    }

    public String getOrderUsername() {
        return orderUsername;
    }

    public void setOrderUsername(String orderUsername) {
        this.orderUsername = orderUsername;
    }

    public String getOrderInitialPosition() {
        return orderInitialPosition;
    }

    public void setOrderInitialPosition(String orderInitialPosition) {
        this.orderInitialPosition = orderInitialPosition;
    }

    public String getOrderFinalPosition() {
        return orderFinalPosition;
    }

    public void setOrderFinalPosition(String orderFinalPosition) {
        this.orderFinalPosition = orderFinalPosition;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getAddGenerationOfDriving() {
        return addGenerationOfDriving;
    }

    public void setAddGenerationOfDriving(String addGenerationOfDriving) {
        this.addGenerationOfDriving = addGenerationOfDriving;
    }

    public String getCharterCarService() {
        return charterCarService;
    }

    public void setCharterCarService(String charterCarService) {
        this.charterCarService = charterCarService;
    }

    public String getPackageTimeDriving() {
        return packageTimeDriving;
    }

    public void setPackageTimeDriving(String packageTimeDriving) {
        this.packageTimeDriving = packageTimeDriving;
    }

    public Double getInitialPositionLatitude() {
        return initialPositionLatitude;
    }

    public void setInitialPositionLatitude(Double initialPositionLatitude) {
        this.initialPositionLatitude = initialPositionLatitude;
    }

    public Double getInitialPositionLongitude() {
        return initialPositionLongitude;
    }

    public void setInitialPositionLongitude(Double initialPositionLongitude) {
        this.initialPositionLongitude = initialPositionLongitude;
    }

    public Double getFinalPositionLatitude() {
        return finalPositionLatitude;
    }

    public void setFinalPositionLatitude(Double finalPositionLatitude) {
        this.finalPositionLatitude = finalPositionLatitude;
    }

    public Double getFinalPositionLongitude() {
        return finalPositionLongitude;
    }

    public void setFinalPositionLongitude(Double finalPositionLongitude) {
        this.finalPositionLongitude = finalPositionLongitude;
    }

    public boolean isIfFinish() {
        return ifFinish;
    }

    public void setIfFinish(boolean ifFinish) {
        this.ifFinish = ifFinish;
    }

    public boolean isIfAccept() {
        return ifAccept;
    }

    public void setIfAccept(boolean ifAccept) {
        this.ifAccept = ifAccept;
    }

    public String getOrderCreationTime() {
        return orderCreationTime;
    }

    public void setOrderCreationTime(String orderCreationTime) {
        this.orderCreationTime = orderCreationTime;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderPortrait='" + orderPortrait + '\'' +
                ", orderUsername='" + orderUsername + '\'' +
                ", orderInitialPosition='" + orderInitialPosition + '\'' +
                ", orderFinalPosition='" + orderFinalPosition + '\'' +
                ", orderPhone='" + orderPhone + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", addGenerationOfDriving='" + addGenerationOfDriving + '\'' +
                ", charterCarService='" + charterCarService + '\'' +
                ", packageTimeDriving='" + packageTimeDriving + '\'' +
                ", initialPositionLatitude=" + initialPositionLatitude +
                ", initialPositionLongitude=" + initialPositionLongitude +
                ", finalPositionLatitude=" + finalPositionLatitude +
                ", finalPositionLongitude=" + finalPositionLongitude +
                ", ifFinish=" + ifFinish +
                ", ifAccept=" + ifAccept +
                ", orderCreationTime='" + orderCreationTime + '\'' +
                ", creationTime='" + creationTime + '\'' +
                '}';
    }
}
