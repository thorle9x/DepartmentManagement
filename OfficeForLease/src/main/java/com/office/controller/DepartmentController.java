package com.office.controller;

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

import com.office.common.HttpStatusEnum;
import com.office.common.ServerResponse;
import com.office.model.dto.DepartmentDTO;
import com.office.model.dto.RoomDTO;
import com.office.service.DepartmentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;

@RestController
@RequestMapping("/department")
public class DepartmentController extends AbstractController {
	@Autowired
	DepartmentService departmentService;

	@ApiOperation(value = "Create new Deparment", notes = "Create new Deparment")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@PostMapping()
	public ResponseEntity<ServerResponse> create(@RequestBody DepartmentDTO model, Principal principal) {
		try {
			// add create by, updated by
			model.setCreatedBy(getPrincipalName(principal));
			model.setUpdatedBy(getPrincipalName(principal));
			DepartmentDTO department = departmentService.save(model);
			return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS).setResult(department), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.UNEXPECTED), HttpStatus.FORBIDDEN);
		}
	}

	@ApiOperation(value = "Get Deparment by id", notes = "Get Deparment by id")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@GetMapping("/{id}")
	public ResponseEntity<ServerResponse> findById(@PathVariable Long id) {
		DepartmentDTO deparmentDTO = null;
		try {
			deparmentDTO = departmentService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.NO_RECORD_FOUND, id).setResult(deparmentDTO), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS).setResult(deparmentDTO), HttpStatus.OK);
	}

	@ApiOperation(value = "Get All Deparment", notes = "Get All Deparment")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@GetMapping()
	public ResponseEntity<ServerResponse> getDeparments() {
		return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS).setResult(departmentService.findAll()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get Deparment by id", notes = "Get Deparment by id")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@GetMapping("/{id}/rooms")
	public ResponseEntity<ServerResponse> getAllRoom(@PathVariable Long id) {
		List<RoomDTO> roomDto = null;
		try {
			roomDto = departmentService.getAllRoomById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.NO_RECORD_FOUND, id).setResult(roomDto), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS).setResult(roomDto), HttpStatus.OK);
	}
}
