package com.expenses_traker.db_service.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenses_traker.db_service.Entity.testData;
import com.expenses_traker.db_service.Repository.TestRepository;
@Service
public class TestService {
    
    @Autowired
    private TestRepository testRepository;

    public List<testData> getTestdata() {
        List<testData> data = testRepository.findAll();
        return data;
    }
    
}
