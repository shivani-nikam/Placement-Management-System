package com.anudip.placement_management_system.service.impl;

import com.anudip.placement_management_system.dto.company.CompanyRequest;
import com.anudip.placement_management_system.dto.company.CompanyResponse;
import com.anudip.placement_management_system.entity.Company;
import com.anudip.placement_management_system.exception.CompanyNotFoundException;
import com.anudip.placement_management_system.mapper.CompanyMapper;
import com.anudip.placement_management_system.repository.CompanyRepository;
import com.anudip.placement_management_system.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyServiceImpl(
            CompanyRepository companyRepository,
            CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    @Override
    public CompanyResponse create(CompanyRequest request) {
        if (companyRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Company company = new Company();
        companyMapper.updateEntity(company, request);

        return companyMapper.toResponse(companyRepository.save(company));
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponse getById(Long id) {
        return companyMapper.toResponse(findCompany(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponse> getAll() {
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyResponse update(Long id, CompanyRequest request) {
        Company company = findCompany(id);
        companyMapper.updateEntity(company, request);

        return companyMapper.toResponse(companyRepository.save(company));
    }

    @Override
    public void delete(Long id) {
        companyRepository.delete(findCompany(id));
    }

    private Company findCompany(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }
}
