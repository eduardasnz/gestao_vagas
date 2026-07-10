package com.eduardasnz.gestao_vagas.expections;

public class CompanyFoundExpection extends RuntimeException{
    public CompanyFoundExpection() {
        super("Company já existe.");
    }
}
