package cn.bubbletg.xxadj.entity;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/13 15:52
 * @description：订单实体类，用户发布代驾产生的订单实体类。
 * @modified By ：
 * @version: 1.0.0
 */
public class Order {
    /**
     * id 订单的 id
     */
    private int id;
    /**
     * 头像，下单着的头像地址
     */
    private String portrait;
    /**
     * 用户名,保存下单着的用户名
     */
    private String username;
    /**
     * 起始位置,订单信息的起始位置
     */
    private String initialPosition;
    /**
     * 终点位置,订单信息的终点位置
     */
    private String finalPosition;
    /**
     * 联系方式,订单信息的联系方式
     */
    private String phone;
    /**
     * 预约时间,订单信息的预约时间
     */
    private String time;
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
    private String initialPositionLatitude;
    /**
     * 起始位置经度,订单信息的起始位置经度
     */
    private String initialPositionLongitude;
    /**
     * 终点位置纬度,订单信息的终点位置纬度
     */
    private String finalPositionLatitude;
    /**
     * 终点位置经度,订单信息的终点位置经度
     */
    private String finalPositionLongitude;
    /**
     * //表示是否完成
     */
    private boolean ifFinish;
    /**
     * 表示是否被接单
     */
    private boolean ifAccept;
    /**
     * 接单人,订单信息的接单人，表示此订单被谁接单，
     */
    private String receivedBy;
    /**
     * 接单表的id,订单信息的接单表的id---------------关联表，后期改-----------------------
     */
    private String daijiajiedan_id;
    /**
     * 指定司机信息id,订单信息的指定司机信息id
     */
    private String zhidingsiji;
    /**
     * 创建时间,订单信息的创建时间
     */
    private String creationTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(String initialPosition) {
        this.initialPosition = initialPosition;
    }

    public String getFinalPosition() {
        return finalPosition;
    }

    public void setFinalPosition(String finalPosition) {
        this.finalPosition = finalPosition;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getInitialPositionLatitude() {
        return initialPositionLatitude;
    }

    public void setInitialPositionLatitude(String initialPositionLatitude) {
        this.initialPositionLatitude = initialPositionLatitude;
    }

    public String getInitialPositionLongitude() {
        return initialPositionLongitude;
    }

    public void setInitialPositionLongitude(String initialPositionLongitude) {
        this.initialPositionLongitude = initialPositionLongitude;
    }

    public String getFinalPositionLatitude() {
        return finalPositionLatitude;
    }

    public void setFinalPositionLatitude(String finalPositionLatitude) {
        this.finalPositionLatitude = finalPositionLatitude;
    }

    public String getFinalPositionLongitude() {
        return finalPositionLongitude;
    }

    public void setFinalPositionLongitude(String finalPositionLongitude) {
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

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public String getDaijiajiedan_id() {
        return daijiajiedan_id;
    }

    public void setDaijiajiedan_id(String daijiajiedan_id) {
        this.daijiajiedan_id = daijiajiedan_id;
    }

    public String getZhidingsiji() {
        return zhidingsiji;
    }

    public void setZhidingsiji(String zhidingsiji) {
        this.zhidingsiji = zhidingsiji;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", portrait='" + portrait + '\'' +
                ", username='" + username + '\'' +
                ", initialPosition='" + initialPosition + '\'' +
                ", finalPosition='" + finalPosition + '\'' +
                ", phone='" + phone + '\'' +
                ", time='" + time + '\'' +
                ", addGenerationOfDriving='" + addGenerationOfDriving + '\'' +
                ", charterCarService='" + charterCarService + '\'' +
                ", packageTimeDriving='" + packageTimeDriving + '\'' +
                ", initialPositionLatitude='" + initialPositionLatitude + '\'' +
                ", initialPositionLongitude='" + initialPositionLongitude + '\'' +
                ", finalPositionLatitude='" + finalPositionLatitude + '\'' +
                ", finalPositionLongitude='" + finalPositionLongitude + '\'' +
                ", ifFinish=" + ifFinish +
                ", ifAccept=" + ifAccept +
                ", receivedBy='" + receivedBy + '\'' +
                ", daijiajiedan_id='" + daijiajiedan_id + '\'' +
                ", zhidingsiji='" + zhidingsiji + '\'' +
                ", creationTime='" + creationTime + '\'' +
                '}';
    }
}
