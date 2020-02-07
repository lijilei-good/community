package vip.lijilei.community.dto;

import lombok.Data;
import vip.lijilei.community.model.User;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 * @Date: Create in 2020/2/4
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private String avatarUrl = "http://a1.att.hudong.com/64/28/01300000231030121927289800837.jpg";
    private User user;
}
