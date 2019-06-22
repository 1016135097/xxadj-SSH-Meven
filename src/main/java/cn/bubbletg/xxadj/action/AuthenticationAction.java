package cn.bubbletg.xxadj.action;

import cn.bubbletg.xxadj.entity.Authentication;
import cn.bubbletg.xxadj.service.AuthenticationService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author ：BubbleTg
 * @date ：Created in 2019/6/22 11:41
 * @description：Authentication Action类
 * @modified By：
 * @version: 1.0.0
 */
public class AuthenticationAction extends ActionSupport implements ModelDriven<Authentication> {
    Authentication authentication = new Authentication();
    @Override
    public Authentication getModel() {
        return authentication;
    }

    private AuthenticationService authenticationService;

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * create by: BubbleTg
     * description: 认证插入方法
     * create time: 2019/6/22 13:50
     *
      * @Param: null
     * @return
     */
    public void add(){
        authenticationService.add(authentication);

    }

    /**
     * create by: BubbleTg
     * description: 认证更新方法
     * create time: 2019/6/22 13:52
     *
      * @Param: null
     * @return
     */
    public void update(){
        authenticationService.update(authentication);
    }

    /**
     * create by: BubbleTg
     * description: 查询方法
     * create time: 2019/6/22 13:53
     *
      * @Param: null
     * @return
     */
    public void findOne(){
        authenticationService.findOne(authentication);
    }





}
