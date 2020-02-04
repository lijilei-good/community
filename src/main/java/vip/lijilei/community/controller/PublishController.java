package vip.lijilei.community.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vip.lijilei.community.mapper.QuestionMapper;
import vip.lijilei.community.mapper.UserMapper;
import vip.lijilei.community.model.Question;
import vip.lijilei.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 * @Date: Create in 2020/2/4
 */
@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String publish(@Param("title")String title,
                          @Param("description")String description,
                          @Param("tag")String tag,
                          HttpServletRequest request,
                          Model model){

        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    user = userMapper.selectBytoken(cookie.getValue());
                    request.getSession().setAttribute("user",user);
                    break;
                }
            }
        }

        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }else{
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(System.currentTimeMillis());
            question.setCreator(user.getId());
            questionMapper.create(question);
            return "redirect:/";
        }


    }
}







