package com.department.common;

public class APIUrlUtils {
	// TOKEN
	public static final String USER_TOKE_URL = "/api/user/authenticate";
	public static final String USER_REFRESH_TOKEN = "/api/user/refresh";

	// GENERAL
	public static final String SLASH = "/";
	public static final String API = "api";
	public static final String API_URL = "/api/";
	public static final String USER = "user";
	public static final String GET_ALL = "/all";
	public static final String SIGNUP = "/signup";
	public static final String CREATE = "/create";

	// USER API
	public static final String USER_API_PATTERN = "/api/user";
	public static final String USER_API_GET_ALL = GET_ALL;
	public static final String USER_API_SIGNUP = SIGNUP;
	public static final String USER_API_SIGNUP_URL = USER_API_PATTERN + SIGNUP;

	public static boolean checkAPIUrlValid(String apiUrl) {
		if (!apiUrl.startsWith(API_URL)) {
			return false;
		}

		String[] urlPattern = apiUrl.split(SLASH);
		if (urlPattern.length < 1) {
			return false;
		}

		return true;
	}

}
