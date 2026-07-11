package com.eduardasnz.gestao_vagas.modules.company.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardasnz.gestao_vagas.modules.company.dtos.AuthCompanyDTO;
import com.eduardasnz.gestao_vagas.modules.company.services.AuthCompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/company")
@Tag(name = "Empresas")
public class AuthCompanyController {
    
    @Autowired
    private AuthCompanyService authCompanyService;

    @PostMapping("/auth")
    @Operation(summary = "Autenticação da empresa", description = "Essa função está responsável por authenticar uma empresa")
    public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO authCompanyDTO) {
        try {
            var result = this.authCompanyService.execute(authCompanyDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
