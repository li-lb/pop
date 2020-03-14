package com.cnki.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常信息统一处理，并在错误页面显示
 * @author 76741
 */
@Slf4j
public class CustomException implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView("/error/error");
        e.printStackTrace();
        MyException myExecption = null;
        if (e instanceof MyException) {
            myExecption = (MyException) e;

        } else {
            myExecption = new MyException("未知错误");
        }
        //错误信息
        String message = myExecption.getMessage();
        ModelAndView modelAndView = new ModelAndView();
        //将错误信息传到页面
        modelAndView.addObject("message", message);
        //指向到错误界面
        return mv;
    }
}