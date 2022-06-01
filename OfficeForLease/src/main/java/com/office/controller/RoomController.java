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
import com.office.model.dto.RoomDTO;
import com.office.service.RoomService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;

@RestController
@RequestMapping("/room")
public class RoomController extends AbstractController{
	
	@Autowired
	RoomService roomService;
	
	@ApiOperation(value = "Create new Room", notes = "Create new Room")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@PostMapping()
	public ResponseEntity<ServerResponse> create(@RequestBody RoomDTO model, Principal principal) {
		try {
			// add create by, updated by
			model.setCreatedBy(getPrincipalName(principal));
			model.setUpdatedBy(getPrincipalName(principal));
			RoomDTO roomDto = roomService.save(model);
			return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS).setResult(roomDto), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.UNEXPECTED), HttpStatus.FORBIDDEN);
		}
	}
	
	@ApiOperation(value = "Get Room by id", notes = "Get Room by id")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@GetMapping("/{id}")
	public ResponseEntity<ServerResponse> findById(@PathVariable Long id) {
		RoomDTO roomDto = null;
		try {
			roomDto = roomService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.NO_RECORD_FOUND, id).setResult(roomDto), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS).setResult(roomDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get All Room", notes = "Get All Room")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@GetMapping()
	public ResponseEntity<ServerResponse> getRooms() {
		return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS).setResult(roomService.findAll()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get All Room by Department id", notes = "Get All Room by Department id")
	@ApiResponses(value = { @ApiResponse(code = 401, response = Response.class, message = "INVALID_TOKEN") })
	@GetMapping("/department/{id}")
	public ResponseEntity<ServerResponse> getAllRoom(@PathVariable Long id) {
		List<RoomDTO> listRoom = null;
		try {
			listRoom = roomService.findByDepartmentId(id);
		} catch (Exception e) {
			return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.NO_RECORD_FOUND, id).setResult(listRoom), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ServerResponse(HttpStatusEnum.SUCCESS).setResult(listRoom), HttpStatus.OK);
	}

}
