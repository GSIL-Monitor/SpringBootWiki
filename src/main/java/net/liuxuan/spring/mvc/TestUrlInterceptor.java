/*
 * Copyright (c) 2010-2016.  by Moses   All rights reserved.
 *
 */

package net.liuxuan.spring.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Moses on 2016/2/5.
 */

public class TestUrlInterceptor implements HandlerInterceptor {
    private static Logger log = LoggerFactory.getLogger(TestUrlInterceptor.class);

    public TestUrlInterceptor() {
        log.debug("--------------- TestUrlInterceptor initialize -------------");

//        System.out.println("--------------- TestUrlInterceptor initialize -------------");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {


//        if(request.getRequestURI().equals("/error")){
//            System.out.println("------------------error path");
//            //request.getRequestDispatcher("/invalidPage");
//            response.sendRedirect("/invalidPage");
//        }
//        HandlerMethod  hm = (HandlerMethod) handler;

        String query = request.getQueryString();
        query = (query == null) ? "" : query;
        String url = request.getMethod() + "   " + request.getRequestURL().toString() + "?" + query;

        log.debug("--UrlInterceptor Pre,url:{}--" , url);

//        System.out.println("--UrlInterceptor Pre--"+url);

        return true;
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {


        if (modelAndView == null) {
            modelAndView = new ModelAndView("error");
            throw new Exception("not find this path!!!!!");
        }

        log.debug("--UrlInterceptor post viewname:{} & user:{}--" , modelAndView.getViewName(),request.getRemoteUser());
//        System.out.println("-------------- TestUrlInterceptor post url -----------------" + modelAndView.getViewName());
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (ex != null) {
            System.out.println("-------------- TestUrlInterceptor completion -----------------" + ex.getMessage());
        }
    }
}
