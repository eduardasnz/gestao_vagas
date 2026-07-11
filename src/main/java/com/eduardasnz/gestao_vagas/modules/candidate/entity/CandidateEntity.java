package com.eduardasnz.gestao_vagas.modules.candidate.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "Candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Schema(example = "John Doe", requiredMode = RequiredMode.REQUIRED, description = "Nome do Candidato.")
    private String name;

    @Pattern(regexp = "^\\S+$", message = "Campo [USERNAME] não deve conter espaço.")
    @Schema(example = "johndoe10", requiredMode = RequiredMode.REQUIRED, description = "Username do Candidato.")
    private String username;

    @Email(message = "Campo [EMAIL] deve conter email válido.")
    @Schema(example = "johndoe@example.com", requiredMode = RequiredMode.REQUIRED, description = "Email do Candidato.")
    private String email;

    @Length(min = 1, max = 80)
    @Schema(example = "SenhaSecreta012@", requiredMode = RequiredMode.REQUIRED, description = "Senha do Candidato.")
    private String password;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
