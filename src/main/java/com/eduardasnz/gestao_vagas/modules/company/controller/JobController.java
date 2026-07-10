package com.eduardasnz.gestao_vagas.modules.company.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardasnz.gestao_vagas.modules.company.dtos.CreateJobDTO;
import com.eduardasnz.gestao_vagas.modules.company.entities.JobEntity;
import com.eduardasnz.gestao_vagas.modules.company.services.JobService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/company/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/")
    @PreAuthorize("hasRole('company')")
    @Tag(name = "Vagas", description = "Informações sobre as vagas.")
    @Operation(summary = "Cadastro de vagas", description = "Essa função está responsável por cadastrar vagas dentro da empresa.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = JobEntity.class))
        })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        try {
            var companyId = request.getAttribute("company_id");

            var jobEntity = JobEntity.builder()
                    .description(createJobDTO.getDescription())
                    .companyId(UUID.fromString(companyId.toString()))
                    .benefits(createJobDTO.getBenefits())
                    .level(createJobDTO.getLevel()).build();
            var result = this.jobService.createJob(jobEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
