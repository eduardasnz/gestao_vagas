package com.eduardasnz.gestao_vagas.modules.candidate.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eduardasnz.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.eduardasnz.gestao_vagas.modules.candidate.entity.CandidateEntity;
import com.eduardasnz.gestao_vagas.modules.candidate.services.CandidateService;
import com.eduardasnz.gestao_vagas.modules.candidate.services.ListAllJobsByFilterService;
import com.eduardasnz.gestao_vagas.modules.candidate.services.ProfileCandidateService;
import com.eduardasnz.gestao_vagas.modules.company.entities.JobEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidatos", description = "Informações sobre o candidato.")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @Autowired
    private ListAllJobsByFilterService listAllJobsByFilterService;

    @PostMapping("/")
    @Operation(summary = "Cadastro de candidatos", description = "Essa função está responsável pelo cadastro de candidatos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CandidateEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Candidato já existe.")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.candidateService.createCandidate(candidateEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('candidate')")
    @Operation(summary = "Perfil do candidato", description = "Essa função está responsável por buscar informações do perfil do candidato.")
    @SecurityRequirement(name = "jwt_auth")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "User not found.")
    })
    public ResponseEntity<Object> findUserById(HttpServletRequest req) {
        var candidateId = req.getAttribute("candidate_id");

        try {
            var profile = profileCandidateService.execute(UUID.fromString(candidateId.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('candidate')")
    @Operation(summary = "Listagem de vagas disponíveis", description = "Essa função está responsável pela listagem das vagas de acordo com o filtro aplicado.")
    @SecurityRequirement(name = "jwt_auth")
    public List<JobEntity> findJobByDescription(@RequestParam String filter) {
        return this.listAllJobsByFilterService.execute(filter);
    }
}
