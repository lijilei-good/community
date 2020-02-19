package vip.lijilei.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.lijilei.community.dto.CommentDTO;
import vip.lijilei.community.mapper.CommentMapper;
import vip.lijilei.community.model.Comment;
import vip.lijilei.community.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 * @Date: Create in 2020/2/19
 */
@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @ResponseBody
    @PostMapping("/comment")
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){

        }
        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setParentid(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentor(11);
        comment.setLikeCount(0L);
        commentMapper.insert(comment);
        return comment;
    }

}
