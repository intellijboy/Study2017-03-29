package com.ylz.control;

import com.ylz.com.ylz.code.util.AuthCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuburu on 2017/3/28.
 */
@Controller
@RequestMapping("/code")
public class CodeServletControl {

    @RequestMapping("/servlet")
    public void responseServletCode(HttpServletRequest request, HttpServletResponse response){
        String authCode = AuthCodeUtil.getAuthCode();
        request.getSession().setAttribute("sessionCode", authCode);    //将验证码保存到session中，便于以后验证
        try {
            //发送图片9
            ImageIO.write(AuthCodeUtil.getAuthImg(authCode), "JPEG", response.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Object userLogin(
            @RequestParam("username") String userName,
            @RequestParam("imgcode") String userCode,HttpServletRequest request
            ){
        String sessionCode =String.valueOf(request.getSession().getAttribute("sessionCode"));
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("user",userName);//*
        resultMap.put("sessionCode",sessionCode);
        resultMap.put("userCode",userCode);
        if(userCode.equals(sessionCode)){
            resultMap.put("status","success");
        }else{
            resultMap.put("status","fail");
        }
        return  resultMap;
    }
}
