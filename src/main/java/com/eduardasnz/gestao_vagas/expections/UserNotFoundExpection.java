package com.eduardasnz.gestao_vagas.expections;

public class UserNotFoundExpection extends RuntimeException {
    public UserNotFoundExpection(){
        super("Candidato não existe.");
    }
}
