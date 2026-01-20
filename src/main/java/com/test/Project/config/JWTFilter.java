package com.test.Project.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.test.Project.security.JWTUtil;
import com.test.Project.services.PersonDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final PersonDetailsService personDetailsService;
    @Autowired
    public JWTFilter(JWTUtil jwtUtil, PersonDetailsService personDetailsService) {
        this.jwtUtil = jwtUtil;
        this.personDetailsService = personDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader =request.getHeader("Authorization");
        String uri = request.getRequestURI();
        if(authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer "))
        {
            String jwt = authHeader.substring(7);
            if(jwt.isBlank())
            {
                //response.sendError(HttpServletResponse.SC_BAD_GATEWAY,"Invalid JWT token in Bearer Header");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Invalid JWT token in Bearer Header");
            } else
            {
                try {
                    String username = jwtUtil.validateToken(jwt);
                    String id = jwtUtil.validateTokenId(jwt).toString();
                    if(uri.contains("user"))
                    {
                        if(!uri.contains(id))
                            throw new JWTVerificationException(id);
                    }
                    UserDetails userDetails = personDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
                    if (SecurityContextHolder.getContext().getAuthentication() == null)
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } catch (JWTVerificationException e)
                {

                    response.setStatus(HttpStatus.BAD_GATEWAY.value());
                    response.setCharacterEncoding("UTF-8");
                    response.setHeader("Content-Type", "text/plain;charset=UTF-8");
                    response.getWriter().write("Invalid JWT token");
                    return;
                }

            }


        }
        else {

            if (!request.getRequestURI().contains("login")) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Type", "text/plain;charset=UTF-8");
                response.getWriter().write("Invalid JWT token");
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

}
