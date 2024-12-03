package com.kcv.account.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcv.account.management.dto.SampleDTO;
import com.kcv.account.management.dto.SampleRequest;
import com.kcv.account.management.dto.SampleResponse;
import com.kcv.account.management.repository.ISampleRepository;

@Service
public class SampleServiceImpl implements ISampleService {
    @Autowired
    private ISampleRepository demoProjectRepository;

    @Override
    public SampleResponse addData(SampleRequest request) {

        SampleDTO demoProject = new SampleDTO();
        demoProject.setAge(request.getAge());
        demoProject.setGender(request.getGender());
        demoProject.setMobileNo(request.getMobileNo());
        demoProject.setName(request.getName());

        SampleDTO data = demoProjectRepository.save(demoProject);

        SampleResponse response = new SampleResponse();

        if (data.getId() != null) {
            response.setId(data.getId());
            response.setResponse("Succesfully Data Added!!!!");
        } else {
            response.setResponse("Sorry Error Occured!!!!");
        }

        return response;
    }

    @Override
    public List<SampleDTO> getAllDetails() {
        List<SampleDTO> dataList = demoProjectRepository.findAll();
        return dataList;
    }

    @Override
    public SampleResponse deleteData(SampleRequest request) {
        SampleResponse response = new SampleResponse();
        try {
            demoProjectRepository.deleteById(request.getId());
            response.setResponse("Data Succesfully Deleted!!!!");

        } catch (Exception e) {
            System.err.println(e.getMessage());
            response.setResponse("Sorry Error Occured!!!!");
        }

        return response;
    }

}
