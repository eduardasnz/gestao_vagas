package com.eduardasnz.gestao_vagas.modules.candidate.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardasnz.gestao_vagas.expections.JobNotFoundException;
import com.eduardasnz.gestao_vagas.expections.UserNotFoundExpection;
import com.eduardasnz.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import com.eduardasnz.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import com.eduardasnz.gestao_vagas.modules.candidate.repository.CandidateRepository;
import com.eduardasnz.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idjob) {
        // validar se usuário existe e se a vaga existe
        this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
            throw new UserNotFoundExpection();
        });

        this.jobRepository.findById(idjob).orElseThrow(() -> {
            throw new JobNotFoundException();
        });

        // candidato se inscrever na vaga
        var applyJob = ApplyJobEntity.builder().candidateId(idCandidate).jobId(idjob).build();
        applyJob = applyJobRepository.save(applyJob);

        return applyJob;
    }
}
