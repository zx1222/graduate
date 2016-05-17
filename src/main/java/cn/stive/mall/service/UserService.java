package cn.stive.mall.service;

import cn.stive.mall.exception.LogAndRegsiterException;
import cn.stive.mall.bean.User;
import cn.stive.mall.dao.UserDao;
import cn.stive.mall.redis.UserCacheUtil;
import cn.stive.mall.util.JsonUtil;
import cn.stive.mall.util.MailUtil;
import cn.stive.mall.util.SignatureUtil;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dxt on 16/4/8.
 */
@Service
public class UserService {

    private static Logger LOG = org.slf4j.LoggerFactory.getLogger(UserService.class);

    public static Map<String,Object> user_map = new HashMap<String, Object>();

    @Autowired
    UserDao userDao;

    public User register(User user) throws Exception {
        //TODO 限制,是否注册过 业务逻辑控制

        if(user.getNick_name()==null){
            user.setNick_name(createRadomName());
        }

        long user_id = 0;
        try {
            user_id = userDao.createUser(user);

            LOG.info("[user],[create],[success!],[user:{}]", JsonUtil.toJson(user));

        }catch(Exception e ){

            LOG.error("[user],[create],[Error!],[user:{}],[e:{}]",user,e);
            throw new LogAndRegsiterException("创建新用户失败!");
        }

        try{
           // sendActivieEmail(user.getEmail(),user_id);
        }catch(Exception e){
            LOG.error("[user],[create],[email],[Error!],[user:{}],[e:{}]",user,e);

        }
        return this.userDao.getUserByPassword(user.getEmail(), user.getPassword());	//直接用phone和passowrd查.. 查不到就抛异常




    }


    public User getUserInfo(long user_id){
        return userDao.getUserInfo(user_id);
    }
    public User loginEmail(String email, String password) {
        try{
            User user = this.userDao.getUserByPassword(email, password);	//直接用phone和passowrd查.. 查不到就抛异常
            setToken(user);

            if (LOG.isDebugEnabled()) {
                LOG.debug("login user : " + user);
            }
            return user;				//返回用户信息,附带token. 每次登录token也会不同.
        }catch(EmptyResultDataAccessException e){
            LOG.warn("none user: phone = {}, password = {}", email, password);
            throw new LogAndRegsiterException("用户不存在");
        }
    }

    public void updateUser(User user) throws Exception {
        userDao.updateUser(user);
    }

    public void  activeUser(long user_id){
        userDao.updateUserActive(user_id);
    }

    private  String createRadomName(){
        String name = "by_"+System.currentTimeMillis();
        return name;
    }

    private void sendActivieEmail(String email ,long user_id) throws Exception {
        String title = "狸猫商城用户注册激活";
        String content = "\t感谢您注册狸猫商城!以下是激活链接:http://localhost:8080/user/activie?u_id="+user_id;
        MailUtil.sendMial(email,title,content);

    }

    public String getHomeData(long  user_id){
        String nick_name = userDao.getUserName(user_id);
        return nick_name;
    }

    private void setToken(User user) {
        String token = UserCacheUtil.getServerToken(user.getId());
        if (StringUtils.isEmptyOrWhitespaceOnly(token)) {
            token = SignatureUtil.compressedUUID();
            UserCacheUtil.setServerToken(user.getId(), token);
        }

        user.setToken(token);

    }

    public  int  focus(long user_id,long visitor_id){
       return  userDao.addFocus(user_id,visitor_id);
    }



}
