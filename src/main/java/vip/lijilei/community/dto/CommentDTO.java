package vip.lijilei.community.dto;

import lombok.Data;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 * @Date: Create in 2020/2/19
 */
@Data
public class CommentDTO {
    private Long parentId;
    private Integer type;
    private String comment;
}
