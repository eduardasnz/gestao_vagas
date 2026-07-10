package com.eduardasnz.gestao_vagas.modules.candidate.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eduardasnz.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.eduardasnz.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import com.eduardasnz.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class AuthCandidateService {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequest) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateRequest.username()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Credenciais incorretas.");
                });

        var passwordMatchers = passwordEncoder.matches(authCandidateRequest.password(), candidate.getPassword());

        if (!passwordMatchers) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withClaim("roles", Arrays.asList("candidate"))
                .withSubject(candidate.getId().toString())
                .sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder()
        .access_token(token)
        .expires_in(expiresIn.toEpochMilli())
        .build();

        return authCandidateResponse; 
    }
}
