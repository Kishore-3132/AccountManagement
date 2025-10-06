package com.kcv.account.management.jwt;

import com.kcv.account.management.config.AppConfigProperties;
import com.kcv.account.management.security.MyUser;
import com.kcv.account.management.security.MyUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;
    private final AppConfigProperties appConfigProperties;
    private final MyUserDetailsService myUserDetailsService;

    public JwtFilter(JwtUtil jwtUtil, AppConfigProperties appConfigProperties, MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
        this.appConfigProperties = appConfigProperties;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // allow public endpoints
        // Below are hardcoded public endpoints, you can also configure them in application.properties
//        if (path.startsWith("/unsecure") || path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")
        if (appConfigProperties.getPublicEndpoints().stream().anyMatch(path::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        if (token == null || !jwtUtil.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: JWT token missing/invalid");
            return;
        }

        String username = jwtUtil.extractUsername(token);

        // Load MyUser from DB
        MyUser myUser = (MyUser) myUserDetailsService.loadUserByUsername(username);


//        UsernamePasswordAuthenticationToken authentication =
//                new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Set MyUser as principal
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(myUser, null, myUser.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
