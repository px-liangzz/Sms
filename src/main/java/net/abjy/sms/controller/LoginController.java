package net.abjy.sms.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import net.abjy.sms.redis.RedisServiceImpl;
//import net.abjy.sms.service.impl.DeptServiceImpl;
import net.abjy.sms.utils.ReturnJsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
//    private DeptServiceImpl deptService;

    @Resource
    private RedisServiceImpl redisService;

    private DefaultKaptcha captchaProducer;

    /**
     * 登录界面
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(Model model)
    {
        return "login111";
    }

    /**
     * 登录验证
     * @param request
     * @param username
     * @param passwd
     * @param code
     * @return
     */
    @RequestMapping(value="/checkLogin",method = RequestMethod.POST)
    @ResponseBody
    public ReturnJsonData checkLogin(HttpServletRequest request,
                                     @RequestParam(value = "username", required = true) String username,
                                     @RequestParam(value = "password", required = true) String passwd,
                                     @RequestParam(value = "code", required = true) String code)
    {
        String kaptchaCode = request.getSession().getAttribute("verifyCode") + "";
        log.debug("用户名："+username+"，密码："+passwd+"，验证码："+code+"，SESSION验证码："+kaptchaCode);
        if(!code.equals(kaptchaCode)){
            return ReturnJsonData.buildError(username+"验证码错误，请重试!");
        }
        if(username.equals("")|| username==null)
        {
            return ReturnJsonData.buildError("未输入用户名，请输入!");
        }
        if(passwd.equals("")|| passwd==null)
        {
            return ReturnJsonData.buildError("未输入密码，请输入!");
        }
        ReturnJsonData jsonData =   ReturnJsonData.buildSucess(username+"登录成功!");
        return jsonData;
    }


    /**
     * 图形验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @GetMapping("/kaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaOutputStream = null;
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String verifyCode = captchaProducer.createText();
            httpServletRequest.getSession().setAttribute("verifyCode", verifyCode);
            BufferedImage challenge = captchaProducer.createImage(verifyCode);
            ImageIO.write(challenge, "jpg", imgOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        captchaOutputStream = imgOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @RequestMapping("/hello")
    public String getIndex(Model model){
//        List<DeptEntity> userList = deptService.list(null);
//        log.info(userList.toString());
//        model.addAttribute("attrName",userList);
//        redisService.set("lzz",userList);
        Object ss   =   redisService.get("lzz");
        log.info(ss.toString());
        model.addAttribute("redisName",ss);
        return "hello";
    }
}
