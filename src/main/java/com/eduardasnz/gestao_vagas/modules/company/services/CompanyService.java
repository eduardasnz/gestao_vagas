package com.eduardasnz.gestao_vagas.modules.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eduardasnz.gestao_vagas.expections.CompanyFoundExpection;
import com.eduardasnz.gestao_vagas.modules.company.entities.CompanyEntity;
import com.eduardasnz.gestao_vagas.modules.company.repositories.CompanyRepository;


@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity createCompany(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail()).ifPresent(
            (company) -> {
                throw new CompanyFoundExpection();
            }
        );

        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
}
