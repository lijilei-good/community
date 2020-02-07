package vip.lijilei.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vip.lijilei.community.dto.QuestionDTO;
import vip.lijilei.community.service.QuestionService;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 * @Date: Create in 2020/2/6
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{action}")
    public String question(@PathVariable(name = "action")String action,
                           Model model){

        QuestionDTO questionDTO = questionService.findByQuestionId(Integer.parseInt(action));
        model.addAttribute("questions", questionDTO);
        return "question";
    }
}
