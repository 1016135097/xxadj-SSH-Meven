package cn.bubbletg.xxadj.entity;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/22 11:39
 * @description：证明，证实类，保存用户实名认证和驾驶认证信息。
 * @modified By：
 * @version: 1.0.0
 */
public class Authentication{
    /**
     * id id
     */
    private  int id;

    /**
     *  用户ID，  与表ID一一对应，所以唯一
     */
    private int userId;

    /**
     *  是否身份认证
     */
    private Boolean realNameAuthentication;
    /**
     *  驾驶认证证件地址，正面
     */
    private String realNameAuthenticationFrontUrl;
    /**
     *  驾驶认证证件地址，反面
     */
    private String realNameAuthenticationBackUrl;

    /**
     * 是否驾驶认证
     */
    private Boolean drivingCertification;

    /**
     *  驾驶认证证件地址，正面
     */
    private String drivingCertificationFrontUrl;
    /**
     *  驾驶认证证件地址，反面
     */
    private String drivingCertificationBackUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getRealNameAuthentication() {
        return realNameAuthentication;
    }

    public void setRealNameAuthentication(Boolean realNameAuthentication) {
        this.realNameAuthentication = realNameAuthentication;
    }

    public String getRealNameAuthenticationFrontUrl() {
        return realNameAuthenticationFrontUrl;
    }

    public void setRealNameAuthenticationFrontUrl(String realNameAuthenticationFrontUrl) {
        this.realNameAuthenticationFrontUrl = realNameAuthenticationFrontUrl;
    }

    public String getRealNameAuthenticationBackUrl() {
        return realNameAuthenticationBackUrl;
    }

    public void setRealNameAuthenticationBackUrl(String realNameAuthenticationBackUrl) {
        this.realNameAuthenticationBackUrl = realNameAuthenticationBackUrl;
    }

    public Boolean getDrivingCertification() {
        return drivingCertification;
    }

    public void setDrivingCertification(Boolean drivingCertification) {
        this.drivingCertification = drivingCertification;
    }

    public String getDrivingCertificationFrontUrl() {
        return drivingCertificationFrontUrl;
    }

    public void setDrivingCertificationFrontUrl(String drivingCertificationFrontUrl) {
        this.drivingCertificationFrontUrl = drivingCertificationFrontUrl;
    }

    public String getDrivingCertificationBackUrl() {
        return drivingCertificationBackUrl;
    }

    public void setDrivingCertificationBackUrl(String drivingCertificationBackUrl) {
        this.drivingCertificationBackUrl = drivingCertificationBackUrl;
    }

    @Override
    public String toString() {
        return "Authentication{" +
                "id=" + id +
                ", userId=" + userId +
                ", realNameAuthentication=" + realNameAuthentication +
                ", realNameAuthenticationFrontUrl='" + realNameAuthenticationFrontUrl + '\'' +
                ", realNameAuthenticationBackUrl='" + realNameAuthenticationBackUrl + '\'' +
                ", drivingCertification=" + drivingCertification +
                ", drivingCertificationFrontUrl='" + drivingCertificationFrontUrl + '\'' +
                ", drivingCertificationBackUrl='" + drivingCertificationBackUrl + '\'' +
                '}';
    }
}
