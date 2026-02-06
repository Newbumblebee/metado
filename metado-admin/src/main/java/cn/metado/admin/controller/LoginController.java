package cn.metado.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/login")
    @ResponseBody
    public String index() {
        return "login";
    }
}
