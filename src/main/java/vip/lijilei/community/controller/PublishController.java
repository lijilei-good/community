package vip.lijilei.community.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vip.lijilei.community.mapper.QuestionMapper;
import vip.lijilei.community.model.Question;
import vip.lijilei.community.model.QuestionExample;
import vip.lijilei.community.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 * @Date: Create in 2020/2/4
 */
@Controller
public class PublishController {

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

        // 配置了拦截器,统一在拦截器验证用户是否登录,并的拦截器里面吧user设置到session里面
        User user = (User)request.getSession().getAttribute("user");
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
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
            return "redirect:/";
        }
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")String id, Model model){
        Question question = questionMapper.selectByPrimaryKey(Integer.parseInt(id));
        model.addAttribute("question", question);
        return "/publish_edit";
    }

    @PostMapping("/publishUpdate")
    public String publishUpdate(@Param("title")String title,
                                @Param("description")String description,
                                @Param("tag")String tag,
                                @Param("id")String id){
        Question question = new Question();
        question.setTag(tag);
        question.setTitle(title);
        question.setDescription(description);

        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andIdEqualTo(Integer.parseInt(id));
        questionMapper.updateByExampleSelective(question, example);
        return "redirect:/";
    }


}







