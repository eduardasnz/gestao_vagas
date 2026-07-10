package com.eduardasnz.gestao_vagas.modules.candidate.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardasnz.gestao_vagas.modules.candidate.entity.CandidateEntity;
import com.eduardasnz.gestao_vagas.modules.candidate.services.CandidateService;
import com.eduardasnz.gestao_vagas.modules.candidate.services.ProfileCandidateService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
       try{
        var result = this.candidateService.createCandidate(candidateEntity);

        return ResponseEntity.ok().body(result);
       } catch (Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('candidate')")
    public ResponseEntity<Object> findUserById(HttpServletRequest req) {
        var candidateId = req.getAttribute("candidate_id");

        try {
            var profile = profileCandidateService.execute(UUID.fromString(candidateId.toString()));
            
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
