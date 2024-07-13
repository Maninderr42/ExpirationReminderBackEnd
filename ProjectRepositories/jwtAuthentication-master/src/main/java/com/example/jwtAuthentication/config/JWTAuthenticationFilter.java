package com.example.jwtAuthentication.config;

import com.example.jwtAuthentication.Utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Configuration
public class JWTAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //fetch token from request
        Optional<String> jwtTokenOptional = getTokenFromRequest(request);

        //validate jwt Token from jwtUtils
        jwtTokenOptional.ifPresent(jwtToken->{
            if(JwtUtils.validateToken(jwtToken)){

                //get username from jwt token
                var usernameOptional = JwtUtils.getUsernameFromToken(jwtToken);
                usernameOptional.ifPresent(username->{

                    //fetch user details with the help of username
                    var userDetails =  userDetailsService.loadUserByUsername(username);

                    //create authentication token

                    var authToken = new UsernamePasswordAuthenticationToken
                            (userDetails,null,userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    //set authToken to security context
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                });
            }
        });

        //if token is null we again pass request and response to next filter
        filterChain.doFilter(request, response);
    }


    private Optional<String> getTokenFromRequest(HttpServletRequest request){


        var autHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

       if(StringUtils.hasText(autHeader) && autHeader.startsWith("Bearer")){
           return Optional.of(autHeader.substring(7));
       }
       return Optional.empty();
    }
}
