package com.eduardasnz.gestao_vagas.modules.candidate.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardasnz.gestao_vagas.expections.UserNotFoundExpection;
import com.eduardasnz.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.eduardasnz.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class ProfileCandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
            throw new UserNotFoundExpection();
        });

        var candidateDTO = ProfileCandidateResponseDTO.builder().name(candidate.getName()).username(candidate.getUsername()).email(candidate.getEmail())
            .id(candidate.getId()).build();

        return candidateDTO;
    }
}
