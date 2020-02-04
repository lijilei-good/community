package vip.lijilei.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vip.lijilei.community.dto.QuestionDTO;
import vip.lijilei.community.mapper.UserMapper;
import vip.lijilei.community.model.User;
import vip.lijilei.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.List;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
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

        List<QuestionDTO> questionDTOList = questionService.list();
        model.addAttribute("questions", questionDTOList);
        return "index";
    }
}
