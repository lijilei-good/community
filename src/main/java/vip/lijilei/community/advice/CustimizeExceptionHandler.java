package vip.lijilei.community.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import vip.lijilei.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 * @Date: Create in 2020/2/18
 */
@ControllerAdvice
public class CustimizeExceptionHandler {


    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model){
        if (e instanceof CustomizeException){
            model.addAttribute("message",e.getMessage());
        }else {
            model.addAttribute("message","服务器炸了，请不要再来了");
        }
        return new ModelAndView("error");
    }


}
