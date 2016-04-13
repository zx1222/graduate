package cn.stive.mall.rest;

import cn.stive.mall.Exceptions.AuthorizationException;
import cn.stive.mall.Exceptions.LogAndRegsiterException;
import cn.stive.mall.Exceptions.TokenExpiredException;
import cn.stive.mall.redis.JedisFactory;
import cn.stive.mall.redis.LuckCacheUtil;
import cn.stive.mall.redis.UserCacheUtil;
import cn.stive.mall.util.CommonUtil;
import cn.stive.mall.util.SignatureUtil;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by dxt on 16/4/6.
 */
@Controller
public class BaseHandler {

    private  static Logger LOG = LoggerFactory.getLogger(BaseHandler.class);

    @Autowired
    HttpServletRequest request;


    @PostConstruct
    public static void init(){
        JedisFactory.getInstance();
    }



    public Response success(Object data){
        Response response = new Response();
        response.setData(data);
        response.setMsg(Response.MSG_SUCCESS);
        response.setStatus(Response.CODE_SUCCESS);
        return response;
    }
    public Response success(){
        Response response = new Response();
        response.setMsg(Response.MSG_SUCCESS);
        response.setStatus(Response.CODE_SUCCESS);

        return response;
    }

    @ExceptionHandler({LogAndRegsiterException.class})
    @ResponseBody
    public Response loginException(Exception e, HttpServletResponse response){
        return reps(Response.CODE_USER_ERROR,e.getMessage());
    }

    public Response reps (int code,String msg ){
        Response response = new Response();

        response.setStatus(code);
        response.setMsg(msg);
        return response;
    }
    private static final String KEY_SIGN = "sign";
    private static final String KEY_USER_ID = "user_id";
    /**
     * 用户验证
     */
    public void authorization() {


        String serverSign = null;
        String clientSign = null;
        String token = null;
        String user_id = null;




        @SuppressWarnings("unchecked")
        Enumeration<String> e = request.getParameterNames();
        List<String> keys = new ArrayList<String>();

        while (e.hasMoreElements()) {
            String key = e.nextElement();
            String value = request.getParameter(key);
            if (KEY_SIGN.equals(key)) {         //前端会传sign
                clientSign = value;
                continue;
            }
            if (KEY_USER_ID.equals(key)) {
                user_id = value;
            }
            keys.add(key);
        }

        if (StringUtils.isEmptyOrWhitespaceOnly(clientSign)) {  //没有传sign 验证失败
            throw new AuthorizationException();
        }

        token = LuckCacheUtil.getToken(CommonUtil.toLong(user_id)); //通过user_id 去缓存去token
        if (StringUtils.isEmptyOrWhitespaceOnly(token)) {   //token会过期
            throw new TokenExpiredException();
        }

        StringBuilder signBuffer = new StringBuilder();
        Collections.sort(keys);     //将参数按顺序排列 除去 sign
        for (String key : keys) {
            String value = request.getParameter(key);
            if (StringUtils.isEmptyOrWhitespaceOnly(value)) {
                continue;
            }
            signBuffer.append("&").append(key).append("=").append(value); //组织成字符串
        }
        signBuffer.append("&token=").append(token);         //加上token

        String signStr = signBuffer.substring(1);
        serverSign = SignatureUtil.SHA1(signStr);   //对组合成的字符串进行sha1加密

        if (!clientSign.equals(serverSign)) {       //如果计算出来的值不等于参数传过来的sign 则认证失败.
            LOG.warn("sign not match [{}],[{}]," + signStr + ", url = " + request.getRequestURI(), clientSign,
                    serverSign);
            throw new AuthorizationException();
        }
    }



}
