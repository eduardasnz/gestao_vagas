package com.eduardasnz.gestao_vagas.modules.candidates.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import com.eduardasnz.gestao_vagas.expections.JobNotFoundException;
import com.eduardasnz.gestao_vagas.expections.UserNotFoundExpection;
import com.eduardasnz.gestao_vagas.modules.candidate.entity.CandidateEntity;
import com.eduardasnz.gestao_vagas.modules.candidate.repository.CandidateRepository;
import com.eduardasnz.gestao_vagas.modules.candidate.services.ApplyJobCandidateService;
import com.eduardasnz.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateServiceTest {

    @InjectMocks
    private ApplyJobCandidateService applyJobCandidateService;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Test
    @DisplayName("Não deve ser possivel aplicar uma vaga com candidato inexistente")
    public void nao_deve_ser_possivel_aplicar_uma_vaga_com_candidato_inexistente() {
        try {
            applyJobCandidateService.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundExpection.class);
        }
    }

    @Test
    @DisplayName("Não deve ser possivel se aplicar em uma vaga inexistente")
    public void nao_deve_ser_possivel_se_aplicar_em_uma_vaga_inexistente(){
        var idCandidate = UUID.randomUUID();
        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);
        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));
        try {
            applyJobCandidateService.execute(idCandidate, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }
}
