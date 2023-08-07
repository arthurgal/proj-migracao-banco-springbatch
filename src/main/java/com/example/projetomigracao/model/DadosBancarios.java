package com.example.projetomigracao.model;

public class DadosBancarios {

    private Long pessoa_id;
    private Long agencia;
    private Long conta;
    private String banco;
    private Long id;

    public Long getPessoa_id() {
        return pessoa_id;
    }

    public void setPessoa_id(Long pessoa_id) {
        this.pessoa_id = pessoa_id;
    }

    public Long getAgencia() {
        return agencia;
    }

    public void setAgencia(Long agencia) {
        this.agencia = agencia;
    }

    public Long getConta() {
        return conta;
    }

    public void setConta(Long conta) {
        this.conta = conta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
