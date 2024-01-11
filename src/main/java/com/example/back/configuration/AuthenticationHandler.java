//package com.example.back.configuration;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//public class AuthenticationHandler implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        if (isRequestForUrl(request, "/user/login") || isRequestForUrl(request, "/user/signup")) {
//            return true;
//        }
//
//        String user = (String) request.getSession().getAttribute("user");
//        if (user == null) {
//            response.sendRedirect("/user/login");
//            return false;
//        }
//        return true;
//    }
//
//    private boolean isRequestForUrl(HttpServletRequest request, String url) {
//        String requestURI = request.getRequestURI();
//        String queryString = request.getQueryString();
//        if (queryString == null) {
//            return requestURI.equals(url);
//        } else {
//            return (requestURI + "?" + queryString).equals(url);
//        }
//    }
//}