package vip.lijilei.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.lijilei.community.dto.QuestionDTO;
import vip.lijilei.community.exception.CustomizeException;
import vip.lijilei.community.mapper.QuestionMapper;
import vip.lijilei.community.mapper.UserMapper;
import vip.lijilei.community.model.Question;
import vip.lijilei.community.model.QuestionExample;
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
        List<Question> questionList = questionMapper.selectByExample(new QuestionExample());
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public List<QuestionDTO> findByUserId(Integer id) {
        ArrayList<QuestionDTO> questionDTOList = new ArrayList<>();
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(id);
        List<Question> questionList = questionMapper.selectByExample(example);
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    /**
     * 根据问题的id查出这个问题的全部信息及作者信息
     * @param id
     * @return
     */
    public QuestionDTO findByQuestionId(Integer id){
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null){
            throw new CustomizeException("你找的问题不存在了，换个试试！！！");
        }
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void incView(int i) {
        Question question = questionMapper.selectByPrimaryKey(i);
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andIdEqualTo(i);
        question.setViewCount(question.getViewCount()+1);
        questionMapper.updateByExampleSelective(question, example);
    }
}
