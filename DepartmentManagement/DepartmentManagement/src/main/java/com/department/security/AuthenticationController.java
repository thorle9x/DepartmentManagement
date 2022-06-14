package com.department.security;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.department.exception.AuthenticationException;
import com.department.exception.ExceptionResponse;
import com.department.model.dto.UserDTO;
import com.department.service.UserService;

@RestController
@CrossOrigin(origins = { "http://localhost:5000", "http://localhost:4200", "http://localhost:8080" })
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil tokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	DaoAuthenticationProvider authenProviderDao;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> authentication(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenProviderDao.setUserDetailsService(userDetailsService);

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = tokenUtil.generateToken(userDetails);

		final List<String> roles = userDetails.getAuthorities().stream().map(grantedAuthority -> grantedAuthority.getAuthority()).collect(Collectors.toList());
		UserDTO user = userService.findByUserName(userDetails.getUsername());
		Long userId = null;
		if (user != null) {
			userId = user.getId();
		}

		return ResponseEntity.ok(new JwtResponse(userId, userDetails.getUsername(), token, roles));
	}

	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
		String error = "Bad request";
		if (ex != null) {
			error = ex.getMessage();
		}

		ExceptionResponse apiExResponse = new ExceptionResponse();
		request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.UNAUTHORIZED.value(), RequestAttributes.SCOPE_REQUEST);
		request.setAttribute("ERROR_CODE", error, RequestAttributes.SCOPE_REQUEST);
		request.setAttribute(RequestDispatcher.ERROR_MESSAGE, ex.getCause().getMessage(), RequestAttributes.SCOPE_REQUEST);
		Map<String, Object> body = apiExResponse.getErrorAttributes(request);

		return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}

	private void authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new AuthenticationException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new AuthenticationException("INVALID_CREDENTIALS", e);
		}
	}
}
