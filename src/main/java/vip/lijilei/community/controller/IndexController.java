package vip.lijilei.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vip.lijilei.community.dto.QuestionDTO;
import vip.lijilei.community.service.QuestionService;

import java.util.List;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model){

        List<QuestionDTO> questionDTOList = questionService.list();
        model.addAttribute("questions", questionDTOList);
        return "index";
    }
}
