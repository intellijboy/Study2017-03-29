package com.ylz.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ylz.modal.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuburu on 2017/3/17.
 */
@Controller
@RequestMapping("test")
public class RequestBodyControl {

    @RequestMapping("/json")
    @ResponseBody
    public Object responseJson() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "刘卜铷");
        return map;
    }

    @RequestMapping("/hello")
    public ModelAndView toHelloPage() {
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("nowTime", new Date());
        return modelAndView;
    }


    @RequestMapping(value = "/string", method = RequestMethod.POST)
    @ResponseBody
    public User testRequestBody1(@RequestBody String userStr) {
        User user = JSON.parseObject(userStr, User.class);
        return user;
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public User testRequestBody2(@RequestBody User user) {
        return user;
    }


    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public List<User> testRequestBody3(@RequestBody List<User> users) {
        return users;
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    @ResponseBody
    public User testRequestBody4(@RequestBody User user) {
        return user;
    }


    /**
     * 使用PostMan进行测试了
     * @param user
     * @param operate
     * @return
     */
    @RequestMapping(value = "/path/{operate}", method = RequestMethod.POST)
    @ResponseBody
    public User testRequestBody5(@RequestBody User user,
                                 @PathVariable(value="operate",required = true) String operate,
                                 @RequestParam(value = "date",required = false) String date) {
        System.out.println(operate);
        System.out.println(date);
        return user;
    }

}
