package com.backend.vetApp.Config.Utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
@Component
public class CookieUtil {

    @Autowired
    JwtUtil jwtUtil;

    public  boolean createAuthCookie(Map<String,String>  userClaims, HttpServletResponse response){
        try{
            String AuthCookieToken = jwtUtil.generateToken(userClaims);
            Cookie authCookie = new Cookie("jwtAuthCookie", AuthCookieToken);
            System.out.println("Auth Cookie Token: " + AuthCookieToken);
            authCookie.setMaxAge(36000); // 10 hours
            authCookie.setPath("/");
            authCookie.setAttribute("SameSite", "Lax");
            authCookie.setHttpOnly(true);
            response.addCookie(authCookie);
            return  false;
        }catch (Exception e){
            return false;
        }
    }
}
