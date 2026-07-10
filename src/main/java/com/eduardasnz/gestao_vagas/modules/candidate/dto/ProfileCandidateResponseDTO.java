package com.eduardasnz.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
    
    @Schema(example = "John Doe")
    private String name;
    @Schema(example = "johndoe1")
    private String username;
    @Schema(example = "johndoe@example.com")
    private String email;
    @Schema(example = "58f974cd-609a-4734-8ac7-a2fb478ea78c")
    private UUID id;
}
