/**
 * 
 */
package com.department.controller;

import java.security.Principal;

public class AbstractController {
	  public static final String DUMMY_USER = "dummy";

	  public String getPrincipalName(Principal principal) {
	    return principal == null ? DUMMY_USER : principal.getName();
	  }
	}
