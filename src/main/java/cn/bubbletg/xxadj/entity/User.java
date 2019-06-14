package cn.bubbletg.xxadj.entity;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/14 16:02
 * @description：用户类，封装用户信息
 * @modified By：
 * @version: 1.0.0
 */
public class User {
    /**
     * id,当前记录ID
     */
    private int id;
    /**
     * openid,微信自带的openid
     */
    private String openid;
    /**
     * name,姓名，公实名认证后显示
     */
    private String name;
    /**
     * username,用户名，微信默认昵称
     */
    private String username;
    /**
     * portrait,头像地址，微信默认头像地址
     */
    private String portrait;
    /**
     * phone,手机号码
     */
    private String phone;
    /**
     * age,年龄，实名认证后自动计算
     */
    private String age;
    /**
     * drivingYears,驾龄，驾驶认证后自动计算
     */
    private String drivingYears;
    /**
     * location,位置，表示用户当前所在地，当前位置
     */
    private String location;
    /**
     * realNameAuthentication,表示是否实名认证
     */
    private String realNameAuthentication;
    /**
     * drivingCertification,表示驾驶认证
     */
    private String drivingCertification;
    /**
     * region,地区，表示所在地区，与location有区别，
     * location表示当前所在地，即当前位置
     */
    private String region;
    /**
     * collectTheNumber,收藏数，表示当前用户被收藏的数量
     */
    private Integer collectTheNumber;
    /**
     * visitTheNumber,查看数，访问数
     */
    private Integer visitTheNumber;
    /**
     * commentsTheNumber,评论数
     */
    private Integer commentsTheNumber;
    /**
     * showCard,展示，表示用户信息是否被展示
     */
    private boolean showCard;
    /**
     * joinTime,加入时间
     */
    private String joinTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDrivingYears() {
        return drivingYears;
    }

    public void setDrivingYears(String drivingYears) {
        this.drivingYears = drivingYears;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRealNameAuthentication() {
        return realNameAuthentication;
    }

    public void setRealNameAuthentication(String realNameAuthentication) {
        this.realNameAuthentication = realNameAuthentication;
    }

    public String getDrivingCertification() {
        return drivingCertification;
    }

    public void setDrivingCertification(String drivingCertification) {
        this.drivingCertification = drivingCertification;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getCollectTheNumber() {
        return collectTheNumber;
    }

    public void setCollectTheNumber(Integer collectTheNumber) {
        this.collectTheNumber = collectTheNumber;
    }

    public Integer getVisitTheNumber() {
        return visitTheNumber;
    }

    public void setVisitTheNumber(Integer visitTheNumber) {
        this.visitTheNumber = visitTheNumber;
    }

    public Integer getCommentsTheNumber() {
        return commentsTheNumber;
    }

    public void setCommentsTheNumber(Integer commentsTheNumber) {
        this.commentsTheNumber = commentsTheNumber;
    }

    public boolean isShowCard() {
        return showCard;
    }

    public void setShowCard(boolean showCard) {
        this.showCard = showCard;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", portrait='" + portrait + '\'' +
                ", phone='" + phone + '\'' +
                ", age='" + age + '\'' +
                ", drivingYears='" + drivingYears + '\'' +
                ", location='" + location + '\'' +
                ", realNameAuthentication='" + realNameAuthentication + '\'' +
                ", drivingCertification='" + drivingCertification + '\'' +
                ", region='" + region + '\'' +
                ", collectTheNumber=" + collectTheNumber +
                ", visitTheNumber=" + visitTheNumber +
                ", commentsTheNumber=" + commentsTheNumber +
                ", showCard=" + showCard +
                ", joinTime='" + joinTime + '\'' +
                '}';
    }
}
