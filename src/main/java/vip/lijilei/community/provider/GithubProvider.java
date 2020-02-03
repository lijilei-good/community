package vip.lijilei.community.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import vip.lijilei.community.dto.AccessTokenDTO;
import vip.lijilei.community.dto.GithubUser;

import java.io.IOException;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 */
@Component
public class GithubProvider {

    /**
     * 向github指定的网址，发送post请求。
     * 在try里面，返回的取到的access_token
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String accessTokenStr = response.body().string();
            String accessToken = accessTokenStr.split("&")[0];
            String token = accessToken.split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上一个方法拿到了access_token,根据access_token去拿到用户的name、id、bio
     * @return
     */
    public GithubUser getUser(String accessTokenStr) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessTokenStr)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }

}
