package com.department.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.department.common.APIUrlUtils;

@Component
public class JwtUnAuthenticationResponseEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -8970718410437077606L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		String errorMessage = (String) request.getAttribute(JwtTokenUtil.ERROR_MESSAGE);

		if (APIUrlUtils.checkAPIUrlValid(request.getServletPath())) {
			if (errorMessage == null || errorMessage == "") {
				errorMessage = JwtConstant.JWT_TOKEN_MISSING;
			}
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, errorMessage);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		} else {
			errorMessage = JwtConstant.INVALID_API_URL;
			response.sendError(HttpServletResponse.SC_FORBIDDEN, errorMessage);
		}
	}
}
