package com.kcv.account.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kcv.account.management.dto.SampleDTO;
import com.kcv.account.management.dto.SampleRequest;
import com.kcv.account.management.dto.SampleResponse;
import com.kcv.account.management.service.ISampleService;

@CrossOrigin
@RestController
public class SampleController 
{
	@Autowired
	private ISampleService demoProjectService;
	
	@PostMapping("/addData")
	public ResponseEntity<SampleResponse> addData(@RequestBody SampleRequest request) 
	{
		SampleResponse response = demoProjectService.addData(request);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getAllDetails")
	public ResponseEntity<List<SampleDTO>> getAllDetails() 
	{
		List<SampleDTO> response = demoProjectService.getAllDetails();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/deleteData")
	public ResponseEntity<SampleResponse> deleteData(@RequestBody SampleRequest request) 
	{
		SampleResponse response = demoProjectService.deleteData(request);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
