package com.ylz.control;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuburu on 2017/3/28.
 */
@Controller
@RequestMapping("kaptcha")
public class KaptchaControl {

    @Autowired
    private Producer captchaProducer ;

    @RequestMapping(value = "/code")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println("验证码: " + code );
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public Object login(@RequestParam(value = "/captcha",required = true) String captcha, HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String sessionCode = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String userCode = captcha;
        resultMap.put("sessionCode",sessionCode);
        resultMap.put("yourCode",captcha);
        resultMap.put("status",sessionCode.equals(userCode));
        return resultMap;
    }
}
