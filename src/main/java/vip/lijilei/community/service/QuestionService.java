package vip.lijilei.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.lijilei.community.dto.QuestionDTO;
import vip.lijilei.community.mapper.QuestionMapper;
import vip.lijilei.community.mapper.UserMapper;
import vip.lijilei.community.model.Question;
import vip.lijilei.community.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 * @Date: Create in 2020/2/4
 */
@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;



    /**
     * 使用问题的创建者id,查询user表,得到这个用户的全部信息
     * @return
     */
    public List<QuestionDTO> list(){
        ArrayList<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList = questionMapper.findAll();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public List<QuestionDTO> findByUserId(Integer id) {
        List<Question> questionList = questionMapper.findByUserId(id);
        ArrayList<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
