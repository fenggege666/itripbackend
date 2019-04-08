
package cn.itrip.auth.controller;

import cn.itrip.auth.service.TokenService;
import cn.itrip.beans.dto.Dto;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.ErrorCode;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
public class TokenController {
    private Logger logger = Logger.getLogger(TokenController.class);
    @Resource
    private TokenService tokenService;

    // header="token" 表示请求头中一定要带上token这个key
    @RequestMapping(value = "/validateToken", method= RequestMethod.GET, headers = "token")
    @ResponseBody
    public Dto validate(HttpServletRequest request) {
        try {
            boolean result = tokenService.validate(request.getHeader("user-agent"), request.getHeader("token"));
            logger.info("+++++++++++++++++++++++++++++++result的值为："+result);
            if (result) {
                return DtoUtil.returnSuccess("token有效");
            } else {
                return DtoUtil.returnSuccess("token无效nmxl");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail(e.getMessage(), ErrorCode.AUTH_UNKNOWN);
        }
    }
}
