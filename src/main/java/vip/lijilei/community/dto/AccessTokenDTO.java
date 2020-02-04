package vip.lijilei.community.dto;

import lombok.Data;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
