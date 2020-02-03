package vip.lijilei.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.lijilei.community.dto.AccessTokenDTO;
import vip.lijilei.community.dto.GithubUser;
import vip.lijilei.community.mapper.UserMapper;
import vip.lijilei.community.model.User;
import vip.lijilei.community.provider.GithubProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    /**
     * 第一步时，github会携带code和state主动回调我司的服务器地址
     * 根据拿到的code 加上我司的各种唯一校验码，再去访问github。
     * github会返回access_token
     * @param code
     * @param state
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletResponse response){
        // 根据github的文档,拿到本次登录人的用户名等信息
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String token = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(token);
        if (githubUser != null){
            // 登录成功,则把该用户信息写入到数据库持久化存储
            User user = new User();
            String communityToken = UUID.randomUUID().toString();
            user.setToken(communityToken);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            //把这个登录的人的信息存入到数据库中
            userMapper.insert(user);
            response.addCookie(new Cookie("token",communityToken));
            return "redirect:/";
        }else{
            // 登录失败
            return "redirect:/";
        }
    }

}
