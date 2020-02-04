package vip.lijilei.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import vip.lijilei.community.mapper.UserMapper;
import vip.lijilei.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    User user = userMapper.selectBytoken(cookie.getValue());
                    request.getSession().setAttribute("user",user);
                    break;
                }
            }
        }
        return "index";
    }
}
