package com.eduardasnz.gestao_vagas.expections;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(){
        super("Vaga não existe.");
        }
}
