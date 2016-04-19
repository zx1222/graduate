package cn.stive.mall.rest;

import cn.stive.mall.bean.User;
import cn.stive.mall.service.UserService;
import cn.stive.mall.util.JsonUtil;
import org.apache.http.HttpResponse;
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
        HttpSession session = request.getSession();
        session.setAttribute("user_info",JsonUtil.toJson(user));
        Cookie c = new Cookie("user_info",JsonUtil.toJson(user));
        c.setPath("/");
        response.addCookie(c);
        if(app_id!=1) {
            response.sendRedirect("/admin/articlelist.html");
        }
        return this.success(user);
    }

    @RequestMapping("/user/focus")
    @ResponseBody
    public Response focus(long user_id,long visitor_id){
        return this.success();
    }


}
