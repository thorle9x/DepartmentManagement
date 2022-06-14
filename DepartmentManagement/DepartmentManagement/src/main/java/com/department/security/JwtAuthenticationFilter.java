package com.department.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.department.common.APIUrlUtils;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil tokenUtil;

	@Value("Authorization")
	private String tokenHeader;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		logger.debug("Authentication Request For '{}'", request.getRequestURL());

		if (!APIUrlUtils.checkAPIUrlValid(request.getServletPath())) {
			logger.debug("Invid URL");
			chain.doFilter(request, response);
			return;
		}

		final String requestTokenHeader = request.getHeader(this.tokenHeader);

		String username = null;
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			if (tokenUtil.validateToken(jwtToken, request)) {
				username = tokenUtil.getUsernameFromToken(jwtToken);
				logger.debug("JWT_TOKEN_USERNAME_VALUE '{}'", username);
			}
		} else {
			logger.warn("JWT_TOKEN_DOES_NOT_START_WITH_BEARER_STRING");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				UsernamePasswordAuthenticationToken userPwAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				userPwAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userPwAuthenticationToken);
			} catch (Exception e) {
				request.setAttribute(JwtTokenUtil.ERROR_MESSAGE, JwtConstant.TOKEN_IS_INVALID);
			}
		}

		chain.doFilter(request, response);
	}
}
