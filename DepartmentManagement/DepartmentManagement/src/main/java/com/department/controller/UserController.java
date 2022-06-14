/**
 * 
 */
package com.department.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.department.common.HttpStatusEnum;
import com.department.common.ServerResponse;
import com.department.model.dto.UserDTO;
import com.department.model.search.UserSearchCriteria;
import com.department.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;

/**
 * @author bao.pham
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractController {

	@Autowired
	UserService userService;

	@ApiOperation(value = "Get All Users", notes = "Get All Users")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@GetMapping()
	public ResponseEntity<ServerResponse> getUsers() {
		return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS).setResult(userService.findAll()),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Get user by id", notes = "Get employee by id")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@GetMapping("/{id}")
	public ResponseEntity<ServerResponse> findById(@PathVariable Long id) {
		UserDTO user = null;
		try {
			user = userService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.NO_RECORD_FOUND, id).setResult(user),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS).setResult(user), HttpStatus.OK);
	}

	@ApiOperation(value = "Create new User", notes = "Create new User")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@PostMapping()
	public ResponseEntity<ServerResponse> create(@RequestBody UserDTO model, Principal principal) {
		try {
			// add create by, updated by
			model.setCreatedBy(getPrincipalName(principal));
			model.setUpdatedBy(getPrincipalName(principal));
			UserDTO user = userService.save(model);
			return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS).setResult(user), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.UNEXPECTED), HttpStatus.FORBIDDEN);
		}
	}

	@ApiOperation(value = "Search all user by page", notes = "Search all user by page")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@PostMapping("/search")
	public ResponseEntity<ServerResponse> search(@RequestBody UserSearchCriteria userSearchCriteria) {
		return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS)
				.setResult(userService.search(userSearchCriteria, userSearchCriteria.pageRequest())), HttpStatus.OK);
	}

}