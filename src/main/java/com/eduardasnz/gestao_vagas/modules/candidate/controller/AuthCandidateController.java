package com.eduardasnz.gestao_vagas.modules.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardasnz.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.eduardasnz.gestao_vagas.modules.candidate.services.AuthCandidateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidatos")
public class AuthCandidateController {
    
    @Autowired
    private AuthCandidateService authCandidateService;

    @PostMapping("/auth")
    @Operation(summary = "Autentitação do candidato", description = "Essa função é responsável por autenticar um candidato.")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO){
        try {
            var token = authCandidateService.execute(authCandidateRequestDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
