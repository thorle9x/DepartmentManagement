package com.department.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMethod;

public class APIUrlUtils {
	// TOKEN
	public static final String USER_TOKE_URL = "/api/user/authenticate";
	public static final String USER_REFRESH_TOKEN = "/api/user/refresh";
	public static final String CUSTOMER_TOKE_URL = "/api/customer/authenticate";
	public static final String CUSTOMER_REFRESH_TOKEN = "/api/customer/refresh";

	// GENERAL
	public static final String SLASH = "/";
	public static final String API = "api";
	public static final String API_URL = "/api/";
	public static final String USER = "user";
	public static final String CUSTOMER = "customer";
	public static final String CATEGORY = "category";
	public static final String PRODUCT = "product";
	public static final String ORDER = "order";
	public static final String GET_ALL = "/all";
	public static final String SIGNUP = "/signup";
	public static final String CREATE = "/create";

	public static final String POST = "/create";
	public static final String GET = "/create";

	// USER API
	public static final String USER_API_PATTERN = "/api/user";
	public static final String USER_API_GET_ALL = GET_ALL;
	public static final String USER_API_SIGNUP = SIGNUP;
	public static final String USER_API_SIGNUP_URL = USER_API_PATTERN + SIGNUP;

	// CUSTOMER API
	public static final String CUSTOMER_API_PATTERN = "/api/customer";
	public static final String CUSTOMER_API_GET_ALL = GET_ALL;
	public static final String CUSTOMER_API_SIGNUP = SIGNUP;
	public static final String CUSTOMER_API_SIGNUP_URL = CUSTOMER_API_PATTERN + SIGNUP;

	// CATEGORY API
	public static final String CATEGORY_API_PATTERN = "/api/category";
	public static final String CATEGORY_API_GET_ALL = GET_ALL;
	public static final String CATEGORY_API_CREATE = CREATE;

	// PRODUCT API
	public static final String PRODUCT_API_PATTERN = "/api/product";
	public static final String PRODUCT_API_GET_ALL = GET_ALL;
	public static final String PRODUCT_API_CREATE = CREATE;

	// ORDER API
	public static final String ORDER_API_PATTERN = "/api/order";
	public static final String ORDER_API_GET_ALL = GET_ALL;
	public static final String ORDER_API_CREATE = CREATE;

	public static Set<String> validAPIUrlsPattern = new HashSet<>();

	public static HashMap<String, RequestMethod> ignoreURIs = new HashMap<>();

	public static HashMap<String, RequestMethod> ignoreURIsPattern = new HashMap<>();

	static {
		ignoreURIsPattern.put("^/api/product/\\d+$", RequestMethod.GET);
		ignoreURIsPattern.put("^/api/category/\\d+$", RequestMethod.GET);
		ignoreURIsPattern.put("^/api/category/\\\\d+/product$", RequestMethod.GET);
	}

	static {
		ignoreURIs.put(USER_API_PATTERN + SIGNUP, RequestMethod.POST);
		ignoreURIs.put(CUSTOMER_API_PATTERN + SIGNUP, RequestMethod.POST);
		ignoreURIs.put(PRODUCT_API_PATTERN + PRODUCT_API_GET_ALL, RequestMethod.GET);
		ignoreURIs.put(CATEGORY_API_PATTERN + CATEGORY_API_GET_ALL, RequestMethod.GET);
	}

	static {
		validAPIUrlsPattern.add(USER);
		validAPIUrlsPattern.add(CUSTOMER);
		validAPIUrlsPattern.add(CATEGORY);
		validAPIUrlsPattern.add(PRODUCT);
		validAPIUrlsPattern.add(ORDER);
	}

	public static Set<String> getValidAPIUrl() {
		return validAPIUrlsPattern;
	}

	public static boolean checkAPIUrlValid(String apiUrl) {
		if (!apiUrl.startsWith(API_URL)) {
			return false;
		}

		String[] urlPattern = apiUrl.split(SLASH);
		if (urlPattern.length < 1) {
			return false;
		}

		if (!validAPIUrlsPattern.contains(urlPattern[2])) {
			return false;
		}

		boolean isCagOrOrder = CATEGORY.equalsIgnoreCase(urlPattern[2]) || ORDER.equalsIgnoreCase(urlPattern[2]);
		if (isCagOrOrder) {
			if (urlPattern.length > 5) {
				return false;
			}
		} else {
			if (urlPattern.length > 6) {
				return false;
			}
		}

		return true;
	}

}
