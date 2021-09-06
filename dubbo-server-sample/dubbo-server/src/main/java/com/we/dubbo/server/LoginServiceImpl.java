package com.we.dubbo.server;

/**
 * @author we
 * @date 2021-09-06 09:48
 **/
public class LoginServiceImpl implements ILoginService{

    @Override
    public String login(String userName, String password) {
        // TODO 业务逻辑
        if(userName.equals("admin")&&password.equals("admin")){
            return "SUCCESS";
        }
        return "FAILED";
    }
}
