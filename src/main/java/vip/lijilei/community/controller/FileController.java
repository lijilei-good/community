package vip.lijilei.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import vip.lijilei.community.dto.ImageDTO;
import vip.lijilei.community.provider.UcloudProvider;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 * @Date: Create in 2020/2/20
 */
@Controller
public class FileController {

    @Autowired
    private UcloudProvider ucloudProvider;

    @RequestMapping("/upload")
    @ResponseBody
    public ImageDTO upload(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");

        String fileName = ucloudProvider.file(file.getInputStream(), file.getContentType(), file.getName());
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setMessage("成功");
        imageDTO.setSuccess(1);
        imageDTO.setUrl(fileName);
        return imageDTO;
    }

}
