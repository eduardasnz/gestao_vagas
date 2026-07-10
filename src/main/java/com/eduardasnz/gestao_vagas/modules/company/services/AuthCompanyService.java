package com.eduardasnz.gestao_vagas.modules.company.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eduardasnz.gestao_vagas.modules.company.dtos.AuthCompanyDTO;
import com.eduardasnz.gestao_vagas.modules.company.dtos.AuthCompanyResponseDTO;
import com.eduardasnz.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyService {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Credenciais incorretas.");
                });

        // Verificar se a senha é igual
        var passwordMatchers = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        // se não for igual:
        if (!passwordMatchers) {
            throw new AuthenticationException();
        }
        // se for igual:
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(company.getId().toString())
                .withClaim("roles", Arrays.asList("company"))
                .sign(algorithm);

        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder().access_token(token).expires_in(expiresIn.toEpochMilli()).build();

        return authCompanyResponseDTO;

    }
}
