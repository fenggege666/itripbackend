package cn.itrip.trade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/alipay")
@Controller
public class AlipayController {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "Hello Word success";
    }
}
