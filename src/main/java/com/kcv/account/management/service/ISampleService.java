package com.kcv.account.management.service;


import java.util.List;

import com.kcv.account.management.dto.SampleDTO;
import com.kcv.account.management.dto.SampleRequest;
import com.kcv.account.management.dto.SampleResponse;

public interface ISampleService  {

	public SampleResponse addData(SampleRequest request);
	public List<SampleDTO> getAllDetails();
	public SampleResponse deleteData(SampleRequest request);
}
