package cn.itrip.auth.controller;

import cn.itrip.auth.service.TokenService;
import cn.itrip.auth.service.UserService;

import cn.itrip.beans.dto.Dto;

import cn.itrip.beans.pojo.ItripUser;

import cn.itrip.beans.vo.ItripTokenVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.ErrorCode;
import cn.itrip.common.MD5;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Controller
@RequestMapping("/api")
public class LoginController {
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "sdffsfsdfds";
    }
    private Logger logger = Logger.getLogger(LoginController.class);

    @Resource
    private UserService userService;
     @Resource
     private TokenService tokenService;
    //用来处理登入的请求

    /**
     *
     * @param userCode   用户编码
     * @param password    密码
     * @param request     请求对象
     * @return
     */
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    @ResponseBody
    public Dto login(@RequestParam("name") String userCode,
                     @RequestParam("password")String password,
                     HttpServletRequest request) throws Exception {
        try {
            ItripUser user = userService.login(userCode, MD5.getMd5(password,32));
             if(EmptyUtils.isNotEmpty(user)){ //判断user对象是否不为空
                  String userAgent = request.getHeader("User-Agent");
                  String token = tokenService.generateToken(userAgent,user);   //生成token字符串
                 //以token字符串为key，ItripUser对象为值，保存到Redis缓存数据库中
                  tokenService.save(token,user);
                  long expTime= Calendar.getInstance().getTimeInMillis()+ 2 * 60 * 60 * 1000;//有效时间
                  long genTime= Calendar.getInstance().getTimeInMillis() ;
                 //Calendar.getInstance().getTimeInMillis();返回long型,
                 //表示从1790-1-1 00:00:00到当前时间总共经过的时间的毫秒数。
                 //Dto中的字段与Token的数据结构一致。
                 ItripTokenVO vo = new ItripTokenVO(token,expTime,genTime);
                 return DtoUtil.returnDataSuccess(vo);


             }
             

        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("用户名或密码不正确", ErrorCode.AUTH_AUTHENTICATION_FAILED);
            
        }
        return  null;

    }



}
