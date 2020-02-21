package vip.lijilei.community.provider;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.*;
import cn.ucloud.ufile.bean.BucketResponse;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import cn.ucloud.ufile.http.HttpClient;
import cn.ucloud.ufile.http.OnProgressListener;
import org.springframework.stereotype.Service;
import vip.lijilei.community.exception.CustomizeException;

import java.io.File;
import java.io.InputStream;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 * @Date: Create in 2020/2/20
 */
@Service
public class UcloudProvider {

    public String file(InputStream fileStream, String mimeType, String fileName) {
        try {
            ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(
                    "TOKEN_6ec49b75-21e3-481a-87ee-1a2eb7fc5045",
                    "bc8fb7f5-b899-4d82-8b57-0136c3bc2b8b");
            ObjectConfig config = new ObjectConfig("cn-gd", "ufileos.com");
            PutObjectResultBean response = UfileClient.object(objectAuthorization, config)
                    .putObject(fileStream, mimeType)
                    .nameAs(fileName)
                    .toBucket("lijilei")
                    .setOnProgressListener((bytesWritten, contentLength) -> {
                    })
                    .execute();
            if(response != null && response.getRetCode() == 0){
                String url = UfileClient.object(objectAuthorization, config)
                        .getDownloadUrlFromPrivateBucket(fileName,
                                "lijilei",
                                60 * 60 * 24 * 365)
                        .createUrl();
                return url;
            }else {
                throw new CustomizeException("图片上传的错误");
            }
        } catch (UfileClientException e) {
            e.printStackTrace();
        } catch (UfileServerException e) {
            e.printStackTrace();
        }
        return fileName;
    }


}
