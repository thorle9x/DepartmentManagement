   package com.department.security;

public class JwtAuthorizationUtil {

//	public static void requiredLoggedIn(Class<?> classLogin) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication.getPrincipal().getClass() != classLogin) {
//			throw new ValidationException(JwtConstant.NOT_ALLOW, JwtConstant.PERMISSION_DENIED);
//		}
//	}

//	public static void checkCurrentUserWith(long userId) {
//		requiredLoggedIn(User.class);
//
//		UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		Set<UserRole> userRoles = user.getUserRoles();
//		List<UserRole> userAdmin = userRoles.stream().filter(u -> JwtConstant.ROLE_ADMIN.equalsIgnoreCase(u.getRole().getName())).collect(Collectors.toList());
//		if ((userAdmin == null || userAdmin.isEmpty()) && user.getId() != userId) {
//			throw new ValidationException(JwtConstant.NOT_ALLOW, JwtConstant.PERMISSION_DENIED);
//		}
//	}

}
