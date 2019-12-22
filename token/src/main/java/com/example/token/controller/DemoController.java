package com.example.token.controller;

import com.example.token.pojo.User;
import com.example.token.service.LoginService;
import com.example.token.tool.JsonRESTResult;
import com.example.token.tool.JsonRESTResultMsg;
import com.example.token.tool.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * #
 * # 　　　┏┓　　　┏┓
 * # 　　┏┛┻━━━┛┻┓
 * # 　　┃　　　　　　　 ┃
 * # 　　┃　　　━　　　 ┃
 * # 　　┃　┳┛　┗┳　┃
 * # 　　┃　　　　　　　 ┃
 * # 　　┃　　　┻　　　 ┃
 * # 　　┃　　　　　　　 ┃
 * # 　　┗━┓　　　┏━┛Codes are far away from bugs with the animal protecting
 * # 　　　　┃　　　┃    神兽保佑,代码无bug
 * # 　　　　┃　　　┃
 * # 　　　　┃　　　┗━━━┓
 * # 　　　　┃　　　　　 ┣┓
 * # 　　　　┃　　　　 ┏┛
 * # 　　　　┗┓┓┏━┳┓┏┛
 * # 　　　　　┃┫┫　┃┫┫
 * # 　　　　　┗┻┛　┗┻┛
 */
@RestController
public class DemoController {
    @Autowired
    LoginService loginService;
    JsonRESTResultMsg code;
    @RequestMapping(value = "/logon")
    public JsonRESTResult getUserInfo(HttpSession session , String userName, String password) {
        /**
         * @params [session, userName, password]
         **/
        JsonRESTResult jsonRESTResult = new JsonRESTResult();
        //使用token工具类生成token串
        String token  = TokenUtil.tokenTest(userName,password);
        //根据用户名密码查找用户
        User user = loginService.findUser(userName,password);
        if (user !=null){
            //将用户对象放到session中
            session.setAttribute("USER_INFO",user);
            //用户无操作30分钟需重新登录
            session.setMaxInactiveInterval(60*30);
            Map map = new HashMap<>();
            map.put("USERINFO",user);
            map.put("SESSIONID",session.getId());
            map.put("token",token);
            jsonRESTResult.setCode(JsonRESTResultMsg.DELETE_SPEC_GROUP_FAILED);
            jsonRESTResult.setMsg("登录成功");
            jsonRESTResult.setData(map);
            return jsonRESTResult;
        }else{
            jsonRESTResult.setCode(JsonRESTResultMsg.SPEC_GROUP_CREATE_FAILED);
            jsonRESTResult.setMsg("登录失败,用户名密码错误");
            jsonRESTResult.setData(null);
            return jsonRESTResult;
        }

    }

    @RequestMapping("/judgeLogin")
    public JsonRESTResult judgeLogin(String token,HttpSession session){
        JsonRESTResult jsonRESTResult = new JsonRESTResult();
        if (TokenUtil.verify(token)){
            User user = (User)session.getAttribute("USER_INFO");
            Map<String ,Object> map=new HashMap();
            jsonRESTResult.setCode(JsonRESTResultMsg.SPEC_GROUP_CREATE_FAILED);
            jsonRESTResult.setData(map);
            jsonRESTResult.setMsg("用户登录成功");
        }else {
            jsonRESTResult.setCode(JsonRESTResultMsg.DELETE_SPEC_GROUP_FAILED);
            jsonRESTResult.setMsg("未登录或已过期");
        }
        return jsonRESTResult;
    }
}
