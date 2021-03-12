package br.com.reservahotel.domain;

import java.util.List;

public class Hotel {

    private String nome;
    private int classificacao;
    private List<Estadia> estadias;
    private Double total = 0D;

    public Hotel() {
    }

    public Hotel(String nome, int classificacao, List<Estadia> estadias) {
        this.nome = nome;
        this.classificacao = classificacao;
        this.estadias = estadias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public List<Estadia> getEstadias() {
        return estadias;
    }

    public void setEstadias(List<Estadia> estadias) {
        this.estadias = estadias;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "nome='" + nome + '\'' +
                ", total=" + total +
                ", classificacao=" + classificacao +
                ", estadias=" + estadias +
                '}';
    }
}
