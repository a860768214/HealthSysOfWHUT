package com.whut.health_sys.controller;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whut.health_sys.config.CodeConfig;
import com.whut.health_sys.dataobject.UserDO;
import com.whut.health_sys.response.CommonReturnType;
import com.whut.health_sys.service.UserService;
import com.whut.health_sys.utils.ConvertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@RestController
@RequestMapping("healthsys/user")
@CrossOrigin(origins = {"*"},allowCredentials = "true",allowedHeaders = "*")
public class UserController
{
    @Autowired
    private UserService userService;

    //获取验证码
    @GetMapping("/vcode")
    public CommonReturnType getVcode(@RequestParam("schmail") String schmail,
                                     HttpServletRequest httpServletRequest)
    {
        System.out.println(schmail);

//        获取是哪一类邮箱，只有是武理邮箱才能进行注册/登陆
        try
        {
            String substring = schmail.substring(schmail.indexOf("@"));
            if (!"@whut.edu.cn".equals(substring))
            {
                return CommonReturnType.create(null, "invalid_mail");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "invalid_email_format");
        }


//        生成验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String VCode = String.valueOf(randomInt);
        System.out.println(VCode);

//        设置发件人邮箱
        MailAccount mailAccount = new MailAccount();
        mailAccount.setHost("smtp.qq.com");
        mailAccount.setPort(465);
        mailAccount.setAuth(true);
        mailAccount.setSslEnable(true);
        mailAccount.setFrom("860768214@qq.com");
        mailAccount.setUser("860768214@qq.com");
        mailAccount.setPass("ynwsfemkrjozbdgc");

//        发送邮件
        MailUtil.send(mailAccount, schmail, "【健康理工】登陆/注册", "验证码为" + VCode + ", 30分钟内有效。", false);

        httpServletRequest.getSession().setAttribute(schmail, VCode);
        return CommonReturnType.create("vcode_has_sent");
    }

    //完善信息
    @PostMapping("/complete")
    @Transactional
    public CommonReturnType complete(@RequestParam("schmail") String schmail,
                                           @RequestParam("name") String name,
                                           @RequestParam("gender") String Sgender,
                                           @RequestParam("phone") String phone,
                                           @RequestParam(value = "qq", required = false, defaultValue = "") String qq,
                                           @RequestParam(value = "age", required = false, defaultValue = "") String Sage,
                                           @RequestParam(value = "height", required = false, defaultValue = "") String Sheight,
                                           @RequestParam(value = "weight", required = false, defaultValue = "") String Sweight,
                                           HttpServletRequest httpServletRequest)
    {

        //将用户信息更新
        try
        {
            UserDO user = new UserDO();
            user.setSchmail(schmail);
            user.setName(name);
            user.setGender(Integer.parseInt(Sgender));
            user.setPhone(phone);

            if (!qq.equals(""))
            {
                user.setQq(qq);
            }
            if (!Sage.equals(""))
            {
                user.setAge(Integer.parseInt(Sage));
            }
            if (!Sheight.equals(""))
            {
                user.setHeight(Double.parseDouble(Sheight));
            }
            if (!Sweight.equals(""))
            {
                user.setWeight(Double.parseDouble(Sweight));
            }
            user.setStatus(CodeConfig.STATUS_USER_NORMAL);

            userService.update(user);

            return CommonReturnType.create(ConvertUtil.convertToUserVO(userService.selectByMail(schmail)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @GetMapping("/login")
    public CommonReturnType login(@RequestParam("schmail") String schmail,
                                  @RequestParam("vcode") String vcode,
                                  HttpServletRequest httpServletRequest)
    {
        if(-1==userService.selectByMail(schmail).getStatus())
        {
            return CommonReturnType.create(ConvertUtil.convertToUserVO(userService.selectByMail(schmail)));
        }

        try
        {
            String inSessionVCode = (String) httpServletRequest.getSession().getAttribute(schmail);
            String substring = schmail.substring(schmail.indexOf("@"));

            if (!"@whut.edu.cn".equals(substring))
            {
                return CommonReturnType.create(null, "invalid_mail");
            }
            else if (!StringUtils.equals(inSessionVCode, vcode))
            {
                return CommonReturnType.create(null, "vcode_not_match");
            }

            UserDO userDO = userService.selectByMail(schmail);
            if(userDO ==null)
            {
                userDO=new UserDO();
                userDO.setSchmail(schmail);
                userDO.setCretime(System.currentTimeMillis() + "");
                userDO.setStatus(CodeConfig.STATUS_USER_EXPIRED);
                userService.insertSelective(userDO);
            }

            return CommonReturnType.create(ConvertUtil.convertToUserVO(userService.selectByMail(schmail)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }
    }

    @GetMapping("/info")
    public CommonReturnType getInfo(@RequestParam(value = "schmail",required = false,defaultValue = "") String schmail,
                                    @RequestParam(value = "uid",required = false,defaultValue = "") String Suid)
    {
        try
        {
            if(!"".equals(schmail))
            {
                UserDO userDO = userService.selectByMail(schmail);
                if(userDO==null)
                {
                    return CommonReturnType.create(null,"user_not_exists");
                }
                else
                {
                    return CommonReturnType.create(ConvertUtil.convertToUserVO(userDO));
                }
            }
            else if(!"".equals(Suid))
            {
                UserDO userDO = userService.selectByUserId(Integer.parseInt(Suid));
                if(userDO==null)
                {
                    return CommonReturnType.create(null,"user_not_exists");
                }
                else
                {
                    return CommonReturnType.create(ConvertUtil.convertToUserVO(userDO));
                }
            }
            else
            {
                return CommonReturnType.create(null,"invalid_parameters");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return CommonReturnType.create(null, "unknown_error");
        }

    }
}
