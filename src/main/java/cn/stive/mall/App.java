package cn.stive.mall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dxt on 16/4/6.
 */
@Controller

public class App {

    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello world!";
    }
}
