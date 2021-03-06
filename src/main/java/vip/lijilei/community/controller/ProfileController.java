package vip.lijilei.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vip.lijilei.community.dto.QuestionDTO;
import vip.lijilei.community.model.User;
import vip.lijilei.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 * @Date: Create in 2020/2/6
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          HttpServletRequest request,
                          Model model){
        User user = (User)request.getSession().getAttribute("user");
        if(action.equals("questions")){
            List<QuestionDTO> questionDTOList = questionService.findByUserId(user.getId());
            model.addAttribute("questions",questionDTOList);
            model.addAttribute("sectionName","我的问题");
        }else if (action.equals("reply")){
            model.addAttribute("sectionName", "最新回复");
        }
        return "profile";
    }
}
