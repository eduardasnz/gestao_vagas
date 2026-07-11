package com.eduardasnz.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "Company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Pattern(regexp = "^\\S+$", message = "Campo [USERNAME] não deve conter espaço.")
    @Schema(example = "inovacode")
    private String username;

    @Email(message = "Campo [EMAIL] deve conter email válido.")
    @Schema(example = "contato@inovacode.com")
    private String email;

    @Length(min = 1, max = 80)
    @Schema(example = "@inova2026")
    private String password;
    @Schema(example = "https://www.inovacode.com.br")
    private String website;
    @Schema(example = "InovaCode Technologia")
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
