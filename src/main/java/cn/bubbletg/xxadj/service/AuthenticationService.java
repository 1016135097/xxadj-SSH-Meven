package cn.bubbletg.xxadj.service;

import cn.bubbletg.xxadj.dao.AuthenticationDao;
import cn.bubbletg.xxadj.entity.Authentication;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/22 11:53
 * @description：AuthenticationService
 * @modified By：
 * @version: 1.0.0
 */
public class AuthenticationService {
    private AuthenticationDao authenticationDao;

    public void setAuthenticationDao(AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }


    /**
     * create by: BubbleTg
     * description: 添加
     * create time: 2019/6/22 13:54
     *
     * @return
     * @Param: null
     */
    public void add(Authentication authentication) {

        Authentication authentication1 = null;
        /**
         * 认证信息插入，先判断是否已经存在，存在就修改。因为实名认证与驾驶认证信息保存在同一个表中
         */
        authentication1 = this.findOne(authentication);
        if (authentication1 != null) {
            /*
            不等于空，所以用户至少完成实名认证或者驾驶认证其中一个，
            这里直接更新表就可以,但是更新前需要判断用户提交的数据是实名认证信息插入，还是驾驶人证
            通过提交的数据判断。
             */
            //判断是驾驶认证，还是实名认证
            if(authentication.getDrivingCertification()){
                //驾驶认证,
                authentication1.setDrivingCertification(authentication.getDrivingCertification());
                //修改证件地址反面
                authentication1.setDrivingCertificationBackUrl(authentication.getDrivingCertificationBackUrl());
                //修改证件地址正面
                authentication1.setDrivingCertificationFrontUrl(authentication.getDrivingCertificationFrontUrl());

            }else{
                authentication1.setRealNameAuthentication(authentication.getRealNameAuthentication());
                authentication1.setRealNameAuthenticationBackUrl(authentication.getRealNameAuthenticationBackUrl());
                authentication1.setRealNameAuthenticationFrontUrl(authentication.getRealNameAuthenticationFrontUrl());

            }
            this.update(authentication1);

        } else {
            //不存在，添加新纪录
            authenticationDao.add(authentication);
        }

    }

    /**
     * create by: BubbleTg
     * description: 查询
     * create time: 2019/6/22 13:55
     *
     * @return
     * @Param: null
     */
    public Authentication findOne(Authentication authentication) {
        //由于用户ID与表ID唯一对应，直接使用用户ID查询，
        return authenticationDao.findOne(authentication.getUserId());
    }

    /**
     * create by: BubbleTg
     * description: 更新
     * create time: 2019/6/22 13:56
     *
     * @return
     * @Param: null
     */
    public void update(Authentication authentication) {
        authenticationDao.update(authentication);
    }
}
