package com.department.controller;

import java.security.Principal;
import java.util.List;

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
import com.department.model.dto.CustomerDTO;
import com.department.service.CustomerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;

@RestController
@RequestMapping("/customer")
public class CustomerController extends AbstractController {
	@Autowired
	CustomerService customerService;

	@ApiOperation(value = "Create new Customer", notes = "Create new Customer")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@PostMapping()
	public ResponseEntity<ServerResponse> create(@RequestBody CustomerDTO model, Principal principal) {
		try {
			// add create by, updated by
			model.setCreatedBy(getPrincipalName(principal));
			model.setUpdatedBy(getPrincipalName(principal));
			CustomerDTO customerDto = customerService.save(model);
			return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS).setResult(customerDto), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.UNEXPECTED), HttpStatus.FORBIDDEN);
		}
	}

	@ApiOperation(value = "Get Customer by id", notes = "Get Customer by id")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@GetMapping("/{id}")
	public ResponseEntity<ServerResponse> findById(@PathVariable Long id) {
		CustomerDTO customerDto = null;
		try {
			customerDto = customerService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.NO_RECORD_FOUND, id).setResult(customerDto), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS).setResult(customerDto), HttpStatus.OK);
	}

	@ApiOperation(value = "Get All Customer", notes = "Get All Customer")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@GetMapping()
	public ResponseEntity<ServerResponse> getCustomers() {
		return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS).setResult(customerService.findAll()), HttpStatus.OK);
	}

	@ApiOperation(value = "Get All Customer by Room id", notes = "Get All Customer by Room id")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@GetMapping("/room/{id}")
	public ResponseEntity<ServerResponse> getAllCustomer(@PathVariable Long id) {
		List<CustomerDTO> listCustomer = null;
		try {
			listCustomer = customerService.findByRoomId(id);
		} catch (Exception e) {
			return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.NO_RECORD_FOUND, id).setResult(listCustomer), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS).setResult(listCustomer), HttpStatus.OK);
	}
}
