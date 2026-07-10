package com.eduardasnz.gestao_vagas.expections;

public class UserFoundExpection extends RuntimeException {
    public UserFoundExpection() {
        super("Usuário já existe.");
    }
}
