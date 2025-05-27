package com.backend.vetApp.Config.Security;

import com.backend.vetApp.Config.Utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    protected   void  doFilterInternal( HttpServletRequest request,HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       try{
           if(request.getRequestURI().contains("/auth/")){
                filterChain.doFilter(request, response);
                return;
           }
           else {
               Cookie[] cookieList = request.getCookies();
               if(cookieList.length > 0){
                   for(Cookie cookie : cookieList){
                       if(cookie.getName().equals("jwt-auth-cookie")){
                           String jwtToken = cookie.getValue();
                           Jws<Claims> claimsJws = jwtUtil.validateToken(jwtToken);
                           Authentication auth = getAuthentication(claimsJws.getBody());
                           SecurityContextHolder.getContext().setAuthentication(auth);
                           return;
                       }
                   }
               }
           }


        }catch (Exception e){
           SecurityContextHolder.clearContext();

        }
        filterChain.doFilter(request, response);


    }



    private Authentication getAuthentication(Claims claims ){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(claims.get("role").toString()));
        UserDetails userDetails = new User(claims.get("id").toString(),"", new ArrayList<>());
        return new UsernamePasswordAuthenticationToken(userDetails,"", grantedAuthorities);

    }

}
