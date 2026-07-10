package com.eduardasnz.gestao_vagas.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardasnz.gestao_vagas.modules.company.entities.CompanyEntity;
import com.eduardasnz.gestao_vagas.modules.company.services.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
        try{
            var result = this.companyService.createCompany(companyEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            e.getStackTrace();
            throw e;
            // return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
