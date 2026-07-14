package com.eduardasnz.gestao_vagas.modules.company.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobDTO {

    @Schema(example = "Desenvolvedor Full Stack para atuar na evolução de nossa plataforma de e-commerce e arquitetura de microsserviços.")
    private String description;
    @Schema(example = "Senior")
    private String benefits;
    @Schema(example = "Vale alimentação, plano de saúde, auxílio home office e horário flexível.")
    private String level;
}
