package com.example.application.data.service;

import com.example.application.data.entity.Company;
import com.example.application.data.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompaniesService {
    private final CompanyRepository companyRepository;

    public CompaniesService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }
}
