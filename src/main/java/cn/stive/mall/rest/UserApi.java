package cn.stive.mall.rest;

import cn.stive.mall.bean.User;
import cn.stive.mall.service.UserService;
import cn.stive.mall.util.JsonUtil;
import org.apache.http.HttpResponse;
import org.jboss.netty.handler.codec.base64.Base64Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by dxt on 16/4/8.
 */
@Controller
public class UserApi extends BaseHandler {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(User user, @RequestParam(required = false, defaultValue = "0") int app_id,
                             HttpServletResponse response) throws Exception {
        User user_info = userService.register(user);

        HttpSession session = request.getSession();
        session.setAttribute("user_info",JsonUtil.toJson(user));
        Cookie c = new Cookie("user_info",JsonUtil.toJson(user));
        c.setPath("/");
        response.addCookie(c);
        return this.success();
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    @ResponseBody
    public Response updateUser(User user ,HttpServletResponse response) throws Exception {
        userService.updateUser(user);
        User u =userService.getUserInfo(user.getId());


        Cookie cookie  = setUserInfoCookie(user);
        response.addCookie(cookie);
        return this.success();
    }


    @RequestMapping(value = "/user/active", method = RequestMethod.GET)
    @ResponseBody
    public Response activeUser(long u_id, HttpServletResponse response) throws IOException {
        userService.activeUser(u_id);
        response.sendRedirect("http://localhost:9000/index.html");
        String nick_name = userService.getHomeData(u_id);
        Cookie cookie = new Cookie("nick_name", nick_name);

        response.addCookie(cookie);
        return this.success();
    }



    @RequestMapping(value = "/user/login/email", method = RequestMethod.POST)
    @ResponseBody
    public Response login(
            String email,
            String password, @RequestParam(required = false, defaultValue = "0") int app_id,
            HttpServletResponse response) throws IOException, ServletException {

        User user = this.userService.loginEmail(email, password);

        response.addCookie(setUserInfoCookie(user));

        if(app_id!=1) {
            response.sendRedirect("/admin/articlelist.html");
        }
        return this.success(user);
    }

    private Cookie setUserInfoCookie(User user) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        user.setNick_name(URLEncoder.encode(user.getNick_name(),"UTF-8"));
        if(user.getDescript()!=null) {
            user.setDescript(URLEncoder.encode(user.getDescript(), "UTF-8"));
        }
        session.setAttribute("user_info",JsonUtil.toJson(user));
        System.out.println(JsonUtil.toJson(user));
        Cookie c = new Cookie("user_info",JsonUtil.toJson(user));
        c.setPath("/");
        return c;
    }

    @RequestMapping("/user/focus")
    @ResponseBody
    public Response focusUser(long user_id,long visitor_id){
        int result = userService.focus(user_id,visitor_id);
        if(result ==1) {
            return this.success("focus");
        }else{
            return this.success("unfocus");
        }

    }


}
